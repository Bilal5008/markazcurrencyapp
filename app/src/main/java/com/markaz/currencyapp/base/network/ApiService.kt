package com.markaz.currencyapp.base.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {


    @GET
    suspend fun getAllCurrencies(@Url url: String): Response<Map<String, String>>

}