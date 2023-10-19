package com.markaz.currencyapp.network.base

import com.markaz.currencyapp.network.erros.ApiError


sealed class ApiResponse<out T : BaseApiResponse> {
    data class Success<out T : BaseApiResponse>(val code: Int, val data: T) : ApiResponse<T>()
    data class Error(val error: ApiError) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}