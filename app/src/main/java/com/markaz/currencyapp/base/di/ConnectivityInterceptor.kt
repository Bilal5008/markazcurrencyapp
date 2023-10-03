package com.markaz.currencyapp.base.di

import com.markaz.currencyapp.utils.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

abstract class ConnectivityInterceptor : Interceptor {

    abstract fun onInternetUnavailable()

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!NetworkUtils.isNetworkAvailable){
            onInternetUnavailable()
            throw NoConnectivityException()
        }
        val request: Request = chain.request()
        return chain.proceed(request)
    }
}

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No network available, please check your WiFi or Data connection"
}