package com.markaz.currencyapp.remote

import com.markaz.currencyapp.dto.responsedtos.CurrencyResponse
import com.markaz.currencyapp.local.entities.CurrencyEntity

sealed class CurrencyData {
    data class ApiSuccess(val response: ApiResponse<CurrencyResponse>) : CurrencyData()
    data class LocalData(val entities: List<CurrencyEntity>) : CurrencyData()
    object Loading : CurrencyData()
    data class Error(val message: String) : CurrencyData()
}