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


    @TC
    fun getCurrentTheme(): Int {
        return sharedPreferences.getInt(
            THEME_KEY,
            THEME_DARK
        )
    }

    fun setCurrentTheme(@TC theme: Int) {
        sharedPreferences.edit().putInt(THEME_KEY, theme).apply()
    }


    @IntDef(
        THEME_DARK,
        THEME_LIGHT
    )
    annotation class TC

}