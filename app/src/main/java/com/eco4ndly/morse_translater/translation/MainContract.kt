package com.eco4ndly.morse_translater.translation

import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import com.eco4ndly.morse_translater.base.BasePresenter
import com.eco4ndly.morse_translater.base.BaseView

/**
 * A Sayan Porya code on 28/04/20
 */
interface MainContract {
    /**
     * View contract for {@see MainActivity.kt}
     */
    interface MainView: BaseView {

        /**
         * Sets the given text in param in the English Edit Text
         */
        fun showTranslatedEnglish(translatedEnglishStr: String)

        /**
         * Sets the given text in param in the Morse Code Edit Text
         */
        fun showTranslatedMorse(translatedMorseStr: String)

        /**
         * Shows Toast
         */
        fun showToast(@StringRes stringId: Int)
    }

    /**
     * Presentation contract for {@see MainActivity.kt}
     */
    interface MainPresenter: BasePresenter<MainView> {

        /**
         * When English to Morse Translation Requested
         */
        fun onEnglishToMorseTranslationRequested(englishString: String)

        /**
         * When Morse to English Translation Requested
         */
        fun onMorseToEnglishTranslationRequested(morseString: String)

        /**
         * When User clicks on Clear button for Morse Code Edit Text field
         */
        fun onMorseTextFieldClearRequested()

        /**
         * When User clicks on Clear button for English Edit Text field
         */
        fun onEnglishTextFieldClearRequested()
    }
}