package com.markaz.currencyapp.ui.currencyrates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
class CurrencyRatesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getSerializableExtra("DefaultCurrency")
        setContent {

        }
    }
}
