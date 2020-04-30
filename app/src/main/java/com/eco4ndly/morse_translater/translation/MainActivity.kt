package com.eco4ndly.morse_translater.translation

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.eco4ndly.morse_translater.BuildConfig
import com.eco4ndly.morse_translater.R
import com.eco4ndly.morse_translater.R.string
import com.eco4ndly.morse_translater.base.ThemedActivity
import com.eco4ndly.morse_translater.extensions.clicks
import com.eco4ndly.morse_translater.extensions.textChanges
import com.eco4ndly.morse_translater.translation.MainContract.*
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * A Sayan Porya code on 24/04/20
 *
 * The main class as the name suggests
 */
class MainActivity : ThemedActivity(), MainView {

  lateinit var presenter: MainPresenter
  private lateinit var appUpdateManager: AppUpdateManager

  private val mainScope = MainScope()

  private var englishClearanceRequested: Boolean = false
  private var morseClearanceRequested: Boolean = false

  companion object {
    private const val REQUEST_CODE_FLEXIBLE_UPDATE = 17362
  }

  @ExperimentalCoroutinesApi
  @FlowPreview
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    presenter = MainPresenterImpl()
    presenter.subscribeView(this)
    initViewActions()
    tv_version.text = getString(
        string.version,
        BuildConfig.VERSION_NAME
    )

    appUpdateManager = AppUpdateManagerFactory.create(this)
    appUpdateManager.appUpdateInfo.addOnSuccessListener {
      if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
      ) {
        requestUpdate(it)
      }
    }
  }

  override fun showTranslatedEnglish(translatedEnglishStr: String) {
    et_english.setText(translatedEnglishStr)
  }

  override fun showTranslatedMorse(translatedMorseStr: String) {
    et_morse.setText(translatedMorseStr)
  }

    override fun showToast(stringId: Int) {
        Toast.makeText(this, getString(stringId), Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
    super.onResume()

    appUpdateManager.appUpdateInfo.addOnSuccessListener {
      if (it.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
        appUpdateManager.startUpdateFlowForResult(
            it,
            AppUpdateType.IMMEDIATE,
            this,
            REQUEST_CODE_FLEXIBLE_UPDATE
        )
      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.unSubscribeView()
    mainScope.cancel()
  }

  override fun onActivityResult(
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_CODE_FLEXIBLE_UPDATE) {
      when (resultCode) {
        Activity.RESULT_OK -> { }
        Activity.RESULT_CANCELED -> { }
        ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> { }
      }
    }
  }

  @FlowPreview
  @ExperimentalCoroutinesApi
  private fun initViewActions() {

    fun translateButtonClick() {
      if (et_english.text.toString()
              .trim()
              .isEmpty()
      ) {
        Toast.makeText(this, getString(string.morse_to_english), Toast.LENGTH_SHORT)
            .show()
        initiateMorseToEnglishTranslate()
      } else if (et_morse.text.toString()
              .trim()
              .isEmpty()
      ) {
        Toast.makeText(this, getString(string.english_to_morse), Toast.LENGTH_SHORT)
            .show()
        initiateEnglishToMorseTranslate()
      } else {
        if (et_morse.isFocused) {
          Toast.makeText(
              this,
              getString(string.morse_to_english),
              Toast.LENGTH_SHORT
          )
              .show()
          initiateMorseToEnglishTranslate()
        } else if (et_english.isFocused) {
          Toast.makeText(
              this,
              getString(string.english_to_morse),
              Toast.LENGTH_SHORT
          )
              .show()
          initiateEnglishToMorseTranslate()
        }
      }
    }


    clicks(
        btn_translate,
        btn_copy_english,
        btn_copy_morse,
        btn_clean_english,
        btn_clean_morse,
        btn_theme_toggle,
        btn_about,
        mainScope = mainScope
    ) { clickedViewId ->
      when (clickedViewId) {
        R.id.btn_translate -> {
          translateButtonClick()
        }
        R.id.btn_copy_english -> {
          val english = et_english.text.toString()
              .trim()
          if (english.isNotEmpty()) {
            copyToClipBoard(english.trim())
          }
        }
        R.id.btn_copy_morse -> {
          val morse = et_morse.text.toString()
              .trim()
          if (morse.isNotEmpty()) {
            copyToClipBoard(morse.trim())
          }
        }
        R.id.btn_clean_english -> {
          englishClearanceRequested = true
          presenter.onEnglishTextFieldClearRequested()
        }
        R.id.btn_clean_morse -> {
          morseClearanceRequested = true
          presenter.onMorseTextFieldClearRequested()
        }
        R.id.btn_theme_toggle -> {
          toggleTheme()
        }
        R.id.btn_about -> {
          showAboutDialog()
        }
      }
    }

    et_english.textChanges()
        .debounce(400)
        .onEach {
          if (et_english.isFocused && !englishClearanceRequested) {
            initiateEnglishToMorseTranslate()
          }
          englishClearanceRequested = false
        }
        .launchIn(mainScope)


    et_morse.textChanges()
        .debounce(400)
        .onEach {
          if (et_morse.isFocused && !morseClearanceRequested) {
            initiateMorseToEnglishTranslate()
          }
          morseClearanceRequested = false
        }
        .launchIn(mainScope)
  }

  private fun initiateEnglishToMorseTranslate() {
    val englishText = et_english.text.toString()
        .trim()
    presenter.onEnglishToMorseTranslationRequested(englishText)
  }

  private fun initiateMorseToEnglishTranslate() {
    val morseString = et_morse.text.toString()
        .trim()
    presenter.onMorseToEnglishTranslationRequested(morseString)
  }

  private fun copyToClipBoard(text: String) {
    val clipboard: ClipboardManager =
      getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label", text)
    clipboard.setPrimaryClip(clip)

    Toast.makeText(this, getString(string.copied_to_clipboard), Toast.LENGTH_SHORT)
        .show()
  }

  @SuppressLint("InflateParams")
  private fun showAboutDialog() {
    val view = LayoutInflater.from(this)
        .inflate(R.layout.layout_about, null, false)
    AlertDialog.Builder(this)
        .setView(view)
        .setCancelable(true)
        .setOnDismissListener {

        }
        .show()
  }

  private fun requestUpdate(appUpdateInfo: AppUpdateInfo?) {
    appUpdateManager.startUpdateFlowForResult(
        appUpdateInfo,
        AppUpdateType.IMMEDIATE,
        this,
        REQUEST_CODE_FLEXIBLE_UPDATE
    )
  }
}
