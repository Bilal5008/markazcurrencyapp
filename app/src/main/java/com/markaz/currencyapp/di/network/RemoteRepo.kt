package com.markaz.currencyapp.di.network

import javax.inject.Inject

class RemoteRepo @Inject constructor(private val apiService: CurrencyRetroApi) {

    /** Get All Currency List **/
    suspend fun getAllCurrencies(url: String) = apiService.getAllCurrencies(url)
    suspend fun getLatestCurrencyRates(url : String, appId: String)  = apiService.getLatestCurrencyRates(url, appId)


}