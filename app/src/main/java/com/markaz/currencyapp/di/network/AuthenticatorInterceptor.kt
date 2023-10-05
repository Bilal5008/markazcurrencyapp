package com.markaz.currencyapp.di.network

import com.google.gson.Gson
import com.markaz.currencyapp.di.application.MarkazCurrencyApplication
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticatorInterceptor(val application: MarkazCurrencyApplication) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        when (response.code) {
            in 400..600 -> {
                try {
                    val error = Gson().fromJson(response.peekBody(Long.MAX_VALUE).string(), ErrorHandling::class.java)

                } catch (e: Exception) {
                }
            }
        }
        return response
    }


}

data class ErrorHandling(var error: String, var success: String)