package com.markaz.currencyapp.di.network

import com.markaz.currencyapp.dto.responsedtos.CurrencyRateResponse
import com.markaz.currencyapp.dto.responsedtos.CurrencyResponse
import com.markaz.currencyapp.remote.ApiResponse
import com.markaz.currencyapp.ui.uilayer.CurrencyResult


interface CurrencyApi {
    suspend fun getAllCurrencies( url: String): CurrencyResult
    suspend fun getLatestCurrencyRates( url: String, appId: String):  ApiResponse<CurrencyRateResponse>

}