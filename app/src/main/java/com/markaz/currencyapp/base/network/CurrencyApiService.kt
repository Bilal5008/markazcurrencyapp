package com.markaz.currencyapp.base.network

import com.markaz.currencyapp.remote.CurrencyRepoImpl
import retrofit2.Response
import retrofit2.http.GET


interface CurrencyApiService {

    @GET(CurrencyRepoImpl.URL_GET_CURRENCIES)
    suspend fun getAllCurrencies(): Response<Map<String, String>>

//    @GET(CurrencyRepo.URL_GET_RATES)
//    suspend fun fetchLatestExchangeRates(
//        @Query("app_id") appId: String
//    ): Response<RateResponse>

}