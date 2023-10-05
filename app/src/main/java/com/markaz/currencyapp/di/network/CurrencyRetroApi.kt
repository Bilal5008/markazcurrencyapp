package com.markaz.currencyapp.di.network

import com.markaz.currencyapp.dto.responsedtos.CurrencyRateResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface CurrencyRetroApi {

    @GET
    suspend fun getAllCurrencies(@Url url: String): Response<Map<String, String>>
    @GET
    suspend fun getLatestCurrencyRates(@Url url: String,@Query("app_id") appId: String):Response<CurrencyRateResponse>

}