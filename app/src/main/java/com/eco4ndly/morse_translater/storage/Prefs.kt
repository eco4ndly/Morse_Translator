package com.eco4ndly.morse_translater.storage

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.IntDef

/**
 * A Sayan Porya code on 24/04/20
 */
class Prefs private constructor(context: Context) {

    var sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "com.eco4ndly.morse_trans",
        Context.MODE_PRIVATE
    )

    companion object {
        @Volatile
        private var INSTANCE: Prefs? = null
        private const val THEME_KEY = "app_theme_key"

        /**
         * Returns lazily initialized Prefs instance, thread safely
         */
        @Synchronized
        fun getInstance(application: Application): Prefs {
            if (INSTANCE == null) {
                synchronized(Prefs::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            Prefs(
                                application
                            )
                    }
                }
            }
            return INSTANCE!!
        }

        const val THEME_DARK = 1
        const val THEME_LIGHT = 0
    }


    /**
     * Returns the currently saved/selected theme in shared preference
     */
    @TC
    fun getCurrentTheme(): Int {
        return sharedPreferences.getInt(
            THEME_KEY,
            THEME_DARK
        )
    }

    /**
     * Saves the given theme in param as the current theme
     */
    fun setCurrentTheme(@TC theme: Int) {
        sharedPreferences.edit().putInt(THEME_KEY, theme).apply()
    }


    /**
     * Denotes the type of themes available to choose
     * This is used so that no other int value could be passed other than these as theme value
     */
    @IntDef(
        THEME_DARK,
        THEME_LIGHT
    )
    annotation class TC

}