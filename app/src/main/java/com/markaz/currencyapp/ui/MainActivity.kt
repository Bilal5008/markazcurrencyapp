package com.markaz.currencyapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.work.Constraints
import androidx.work.NetworkType
import com.markaz.currencyapp.ui.currencyrates.CurrencyRatesActivity
import com.markaz.currencyapp.viewmodel.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: CurrencyViewModel by viewModels()
    private lateinit var intent: Intent
    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(viewModel) {
                intent = Intent(this, CurrencyRatesActivity::class.java)
                intent.putExtra("DefaultCurrency", it.currencyCode)
                this.startActivity(intent)
            }

        }
        viewModel.getCurrencyPageData()


    }


}

