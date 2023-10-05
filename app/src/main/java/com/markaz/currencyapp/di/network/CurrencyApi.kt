package com.markaz.currencyapp.di.network

import com.markaz.currencyapp.dto.responsedtos.CurrencyRateResponse
import com.markaz.currencyapp.dto.responsedtos.CurrencyResponse
import com.markaz.currencyapp.remote.ApiResponse


interface CurrencyApi {
    suspend fun getAllCurrencies( url: String): ApiResponse<CurrencyResponse>
    suspend fun getLatestCurrencyRates( url: String, appId: String):  ApiResponse<CurrencyRateResponse>

}