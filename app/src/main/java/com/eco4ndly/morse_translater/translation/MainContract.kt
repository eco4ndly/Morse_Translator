package com.eco4ndly.morse_translater.translation

import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import com.eco4ndly.morse_translater.base.BasePresenter
import com.eco4ndly.morse_translater.base.BaseView

/**
 * A Sayan Porya code on 28/04/20
 */
interface MainContract {
    interface MainView: BaseView {
        fun showTranslatedEnglish(translatedEnglishStr: String)

        fun showTranslatedMorse(translatedMorseStr: String)

        fun showToast(@StringRes stringId: Int)
    }

    interface MainPresenter: BasePresenter<MainView> {
        fun onEnglishToMorseTranslationRequested(englishString: String)

        fun onMorseToEnglishTranslationRequested(morseString: String)

        fun onMorseTextFieldClearRequested()

        fun onEnglishTextFieldClearRequested()

        fun onCopyEnglishTextRequested(englishString: String)

        fun onCopyMorseTextRequested(morseString: String)
    }
}