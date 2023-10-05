package com.markaz.currencyapp.ui.uilayer

import com.markaz.currencyapp.local.entities.CurrencyEntity
import com.markaz.currencyapp.remote.erros.ApiError


sealed class CurrencyResult {
    object Loading : CurrencyResult()

    data class Success(val list: List<CurrencyEntity>) : CurrencyResult()

    data class Error(val error: ApiError) : CurrencyResult()

}
