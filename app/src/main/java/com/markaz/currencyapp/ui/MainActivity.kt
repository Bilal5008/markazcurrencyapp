package com.markaz.currencyapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.markaz.currencyapp.ui.uilayer.AppContainer
import com.markaz.currencyapp.viewmodel.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: CurrencyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContainer(viewModel)
        }
        viewModel.getCurrencyPageData()
    }

}

