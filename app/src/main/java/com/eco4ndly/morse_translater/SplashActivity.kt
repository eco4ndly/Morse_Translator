package com.eco4ndly.morse_translater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eco4ndly.morse_translater.translation.MainActivity

/**
 * A Sayan Porya code on 24/04/20
 *
 * The main class as the name suggests
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }
}
