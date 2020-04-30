package com.eco4ndly.morse_translater.core

/**
 * A Sayan Porya code on 23/04/20
 */
object MorseMap {
    private val morseToEnglishMap = HashMap<String, String>()
    private val englishToMorseMap = HashMap<String, String>()


    /**
     * Converts english character to morse code
     * This method turns the character to lowercase
     *
     * @param toEncode English character to encode
     */
    fun englishToMorse(toEncode: String): String {
        toEncode.toLowerCase().let {
            if (englishToMorseMap.isEmpty()) {
                englishToMorseMap.putAll(buildEnglishToMorseMap())
            }
            if (englishToMorseMap.containsKey(it)) {
                return englishToMorseMap[it]!!
            }

            return ""
        }
    }

    /**
     * Converts morse code character to english
     *
     * @param toDecode Morse code character to decode
     */
    fun morseToEnglish(toDecode: String): String {
        if (morseToEnglishMap.isEmpty()) {
            morseToEnglishMap.putAll(buildMorseToEnglishMap())
        }

        if (morseToEnglishMap.containsKey(toDecode)) {
            return morseToEnglishMap[toDecode]!!
        }

        return ""
    }

    private fun buildMorseToEnglishMap(): HashMap<String, String> {
        val morseToEnglishMap = HashMap<String, String>()
        morseToEnglishMap[".-"] = "a"
        morseToEnglishMap["-..."] = "b"
        morseToEnglishMap["-.-."] = "c"
        morseToEnglishMap["-.."] = "d"
        morseToEnglishMap["."] = "e"
        morseToEnglishMap["..-."] = "f"
        morseToEnglishMap["--."] = "g"
        morseToEnglishMap["...."] = "h"
        morseToEnglishMap[".."] = "i"
        morseToEnglishMap[".---"] = "j"
        morseToEnglishMap["-.-"] = "k"
        morseToEnglishMap[".-.."] = "l"
        morseToEnglishMap["--"] = "m"
        morseToEnglishMap["-."] = "n"
        morseToEnglishMap["---"] = "o"
        morseToEnglishMap[".--."] = "p"
        morseToEnglishMap["--.-"] = "q"
        morseToEnglishMap[".-."] = "r"
        morseToEnglishMap["..."] = "s"
        morseToEnglishMap["-"] = "t"
        morseToEnglishMap["..-"] = "u"
        morseToEnglishMap["...-"] = "v"
        morseToEnglishMap[".--"] = "w"
        morseToEnglishMap["-..-"] = "x"
        morseToEnglishMap["-.--"] = "y"
        morseToEnglishMap["--.."] = "z"
        morseToEnglishMap["-----"] = "0"
        morseToEnglishMap[".----"] = "1"
        morseToEnglishMap["..---"] = "2"
        morseToEnglishMap["...--"] = "3"
        morseToEnglishMap["....-"] = "4"
        morseToEnglishMap["....."] = "5"
        morseToEnglishMap["-...."] = "6"
        morseToEnglishMap["--..."] = "7"
        morseToEnglishMap["---.."] = "8"
        morseToEnglishMap["----."] = "9"
        morseToEnglishMap["|"] = ""
        morseToEnglishMap["/"] = " "
        morseToEnglishMap[" "] = " "
        morseToEnglishMap[".-.-"] = "#"
        morseToEnglishMap[".-.-.-"] = "."
        morseToEnglishMap["--..--"] = ","
        morseToEnglishMap["..--.."] = "?"

        return morseToEnglishMap
    }

    private fun buildEnglishToMorseMap(): HashMap<String, String> {
        val englishToMorseMap = HashMap<String, String>()
        englishToMorseMap["a"] = ".-"
        englishToMorseMap["b"] = "-..."
        englishToMorseMap["c"] = "-.-."
        englishToMorseMap["d"] = "-.."
        englishToMorseMap["e"] = "."
        englishToMorseMap["f"] = "..-."
        englishToMorseMap["g"] = "--."
        englishToMorseMap["h"] = "...."
        englishToMorseMap["i"] = ".."
        englishToMorseMap["j"] = ".---"
        englishToMorseMap["k"] = "-.-"
        englishToMorseMap["l"] = ".-.."
        englishToMorseMap["m"] = "--"
        englishToMorseMap["n"] = "-."
        englishToMorseMap["o"] = "---"
        englishToMorseMap["p"] = ".--."
        englishToMorseMap["q"] = "--.-"
        englishToMorseMap["r"] = ".-."
        englishToMorseMap["s"] = "..."
        englishToMorseMap["t"] = "-"
        englishToMorseMap["u"] = "..-"
        englishToMorseMap["v"] = "...-"
        englishToMorseMap["w"] = ".--"
        englishToMorseMap["x"] = "-..-"
        englishToMorseMap["y"] = "-.--"
        englishToMorseMap["z"] = "--.."
        englishToMorseMap["0"] = "-----"
        englishToMorseMap["1"] = ".----"
        englishToMorseMap["2"] = "..---"
        englishToMorseMap["3"] = "...--"
        englishToMorseMap["4"] = "....-"
        englishToMorseMap["5"] = "....."
        englishToMorseMap["6"] = "-...."
        englishToMorseMap["7"] = "--..."
        englishToMorseMap["8"] = "---.."
        englishToMorseMap["9"] = "----."
        englishToMorseMap["."] = ".-.-.-"
        englishToMorseMap["#"] = ".-.-"
        englishToMorseMap[","] = "--..--"
        englishToMorseMap["?"] = "..--.."
        englishToMorseMap[" "] = " / "

        return englishToMorseMap
    }

}