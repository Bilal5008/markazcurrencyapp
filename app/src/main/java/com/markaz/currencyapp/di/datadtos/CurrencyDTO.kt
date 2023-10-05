package com.markaz.currencyapp.di.datadtos

import com.markaz.currencyapp.remote.BaseDataResponse
import com.task.currencyapp.domain.datadtos.Currency

data class CurrencyDTO(
    val currencies: List<Currency>
) : BaseDataResponse()
