package com.markaz.currencyapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.markaz.currencyapp.ui.currencyrates.CurrencyRatesActivity
import com.markaz.currencyapp.viewmodel.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: CurrencyViewModel by viewModels()
    private lateinit var intent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel.responseStateFlow) {
                intent = Intent(this, CurrencyRatesActivity::class.java)
                intent.putExtra("DefaultCurrency", it.currencyCode)
                this.startActivity(intent)
            }

        }
        viewModel.getCurrencyPageData()

    }
}

