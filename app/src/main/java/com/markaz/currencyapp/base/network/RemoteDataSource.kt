package com.markaz.currencyapp.base.network

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    /** Get All Currency List **/
    suspend fun getAllCurrencies(url: String) = apiService.getAllCurrencies(url)
}