package com.markaz.currencyapp.remote

import com.markaz.currencyapp.dto.BaseApiResponse
import com.markaz.currencyapp.remote.erros.ApiError


sealed class ApiResponse<out T : BaseApiResponse> {
    data class Success<out T : BaseApiResponse>(val code: Int, val data: T) : ApiResponse<T>()
    data class Error(val error: ApiError) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}