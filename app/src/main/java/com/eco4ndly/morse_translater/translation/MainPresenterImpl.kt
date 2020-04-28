package com.eco4ndly.morse_translater.translation

import android.text.TextUtils
import com.eco4ndly.morse_translater.R
import com.eco4ndly.morse_translater.core.TranslatorEngine
import com.eco4ndly.morse_translater.translation.MainContract.*

/**
 * A Sayan Porya code on 28/04/20
 */
class MainPresenterImpl: MainPresenter {
    private var mainView: MainView? = null
    override fun onEnglishToMorseTranslationRequested(englishString: String) {
        if (!TextUtils.isEmpty(englishString.trim())) {
            mainView?.showTranslatedMorse(
                TranslatorEngine.englishToMorse(englishString.trim()))
        }
    }

    override fun onMorseToEnglishTranslationRequested(morseString: String) {
        if (!TextUtils.isEmpty(morseString.trim())) {
            mainView?.showTranslatedEnglish(
                TranslatorEngine.morseToEnglish(morseString.trim()))
        }
    }

    override fun onMorseTextFieldClearRequested() {
        mainView?.showTranslatedMorse("")
    }

    override fun onEnglishTextFieldClearRequested() {
        mainView?.showTranslatedEnglish("")
    }

    override fun onCopyEnglishTextRequested(englishString: String) {
    }

    override fun onCopyMorseTextRequested(morseString: String) {

    }

    override fun subscribeView(view: MainView) {
        this.mainView = view
    }

    override fun unSubscribeView() {
        this.mainView = null
    }
}