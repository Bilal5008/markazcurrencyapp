package com.markaz.currencyapp.ui.uilayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.markaz.currencyapp.viewmodel.CurrencyViewModel

enum class Screen {
    Screen1,
    Screen2
}

@Composable
fun AppContainer(viewModel: CurrencyViewModel) {
    var currentScreen by remember { mutableStateOf(Screen.Screen1) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        when (currentScreen) {
            Screen.Screen1 -> BaseCurrencySelectionScreen(viewModel.responseCurrencyStateFlow) {
                currentScreen = Screen.Screen2
            }
            Screen.Screen2 -> CurrencyRateScreen(viewModel.responseCurrencyRatesStateFlow)

        }
    }
}