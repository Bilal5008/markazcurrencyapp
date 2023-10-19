package com.markaz.currencyapp.dto.apidto

import com.markaz.currencyapp.local.entities.CurrencyEntity
import com.markaz.currencyapp.network.erros.ApiError


sealed class CurrencyResult {
    object Loading : CurrencyResult()

    data class Success(val list: List<CurrencyEntity>) : CurrencyResult()

    data class Error(val error: ApiError) : CurrencyResult()

}
