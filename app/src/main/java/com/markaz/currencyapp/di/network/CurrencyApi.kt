package com.markaz.currencyapp.di.network

import com.markaz.currencyapp.dto.responsedtos.CurrencyResponse
import com.markaz.currencyapp.dto.responsedtos.RateResponse
import com.markaz.currencyapp.remote.ApiResponse
import retrofit2.Response
import retrofit2.http.Url


interface CurrencyApi {
    suspend fun getAllCurrencies( url: String): ApiResponse<CurrencyResponse>
    suspend fun getLatestCurrencyRates( url: String, appId: String):  ApiResponse<CurrencyResponse>

}