package com.markaz.currencyapp.di.network

import com.markaz.currencyapp.dto.apidto.CurrencyRateResponse
import com.markaz.currencyapp.network.base.ApiResponse
import com.markaz.currencyapp.dto.apidto.CurrencyResult


interface CurrencyApi {
    suspend fun getAllCurrencies( url: String): CurrencyResult
    suspend fun getLatestCurrencyRates( url: String, appId: String): ApiResponse<CurrencyRateResponse>

}