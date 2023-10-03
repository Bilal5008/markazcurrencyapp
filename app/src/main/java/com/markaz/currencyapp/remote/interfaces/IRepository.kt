package com.markaz.currencyapp.remote.interfaces


import com.markaz.currencyapp.dto.BaseApiResponse
import com.markaz.currencyapp.remote.ApiResponse
import com.task.currencyapp.data.remote.baseclient.erros.ApiError
import retrofit2.Response


internal interface IRepository {
    suspend fun <T : BaseApiResponse> executeSafely(call: suspend () -> Response<T>): ApiResponse<T>
    suspend fun <T> executeSafelyRaw(call: suspend () -> Response<T>): Response<T>?
    fun <T > detectError(response: Response<T>): ApiError
}