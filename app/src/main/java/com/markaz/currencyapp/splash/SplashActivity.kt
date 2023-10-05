package com.markaz.currencyapp.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.markaz.currencyapp.ui.MainActivity
import com.markaz.currencyapp.utils.Coroutines
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashActivity: ComponentActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Coroutines.default {
            delay(3000)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}