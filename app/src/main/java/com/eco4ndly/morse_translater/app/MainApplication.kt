package com.eco4ndly.morse_translater.app

import android.app.Application
import android.content.Context

/**
 * A Sayan Porya code on 24/04/20
 */
class MainApplication: Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}