package com.eco4ndly.morse_translater.core

/**
 * A Sayan Porya code on 28/04/20
 */

object TranslatorEngine {
    fun englishToMorse(englishStr: String): String {
        val userInputCharArray = englishStr.toLowerCase().split("")
        val morseStringBuilder = StringBuilder()

        for (l in userInputCharArray) {
            val trs = MorseMap.englishToMorse(l)
            morseStringBuilder.append(trs)
            if (!trs.contains("/")) {
                morseStringBuilder.append(" ")
            }
        }

        return morseStringBuilder.toString()
    }

    fun morseToEnglish(morse: String): String {
        val userInputCharArray = morse.split(" ")
        val englishStringBuilder = StringBuilder()

        for (l in userInputCharArray) {
            englishStringBuilder.append(
                MorseMap.morseToEnglish(
                    l
                )
            )
        }

        return englishStringBuilder.toString()
    }
}