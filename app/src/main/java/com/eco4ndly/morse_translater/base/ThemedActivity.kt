package com.eco4ndly.morse_translater.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.eco4ndly.morse_translater.R
import com.eco4ndly.morse_translater.storage.Prefs
import com.eco4ndly.morse_translater.storage.Prefs.TC

/**
 * A Sayan Porya code on 28/04/20
 */
abstract class ThemedActivity: AppCompatActivity() {

  @TC
  private var appTheme: Int = Prefs.THEME_DARK

  override fun onCreate(
    savedInstanceState: Bundle?
  ) {
    super.onCreate(savedInstanceState)
    setAppTheme()
  }

  protected open fun toggleTheme() {
    val newTheme = when (Prefs.getInstance(application)
        .getCurrentTheme()) {
      Prefs.THEME_DARK -> Prefs.THEME_LIGHT
      Prefs.THEME_LIGHT -> Prefs.THEME_DARK
      else -> Prefs.THEME_DARK
    }
    Prefs.getInstance(application)
        .setCurrentTheme(newTheme)

    recreate()
  }


  protected open fun setAppTheme() {
    appTheme = getCurrentTheme()
    when (appTheme) {
      Prefs.THEME_DARK -> {
        setTheme(R.style.AppTheme_Dark)
      }
      Prefs.THEME_LIGHT -> {
        setTheme(R.style.AppTheme_Light)
      }
      else -> {
        setTheme(R.style.AppTheme_Dark)
      }
    }
  }

  @TC
  protected open fun getCurrentTheme(): Int {
    return Prefs.getInstance(application).getCurrentTheme()
  }
}