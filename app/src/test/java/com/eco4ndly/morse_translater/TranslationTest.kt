package com.eco4ndly.morse_translater

import com.eco4ndly.morse_translater.core.TranslatorEngine
import org.junit.Assert
import org.junit.Test

/**
 * A Sayan Porya code on 28/04/20
 */
class TranslationTest {

    @Test
    fun english_to_morse_translation_test_pass() {
        val englishText = "hi"
        Assert.assertEquals(TranslatorEngine.englishToMorse(englishText).trim(), ".... ..")
    }

    @Test
    fun english_to_morse_translation_test_fail() {
        val englishText = "hi"
        Assert.assertNotEquals(TranslatorEngine.englishToMorse(englishText).trim(), ".... _")
    }

    @Test
    fun morse_to_english_translation_test_pass() {
        val morseStringUnderTest = ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
        Assert.assertEquals(TranslatorEngine.morseToEnglish(morseStringUnderTest), "hello world")
    }

    @Test
    fun morse_to_english_translation_test_fail() {
        val morseStringUnderTest = ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
        Assert.assertNotEquals(TranslatorEngine.morseToEnglish(morseStringUnderTest), "hello there")
    }
}