package com.markaz.currencyapp.network.interfaces


import com.markaz.currencyapp.network.base.BaseApiResponse
import com.markaz.currencyapp.network.base.ApiResponse
import com.markaz.currencyapp.network.erros.ApiError
import retrofit2.Response


internal interface IRepository {
    suspend fun <T : BaseApiResponse> executeSafely(call: suspend () -> Response<T>): ApiResponse<T>
    suspend fun <T> executeSafelyRaw(call: suspend () -> Response<T>): Response<T>?
    fun <T > detectError(response: Response<T>): ApiError
}