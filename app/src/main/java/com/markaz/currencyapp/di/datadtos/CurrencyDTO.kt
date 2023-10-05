package com.markaz.currencyapp.di.datadtos

import com.markaz.currencyapp.remote.BaseDataResponse

data class CurrencyDTO(
    val currencies: List<Currency>
) : BaseDataResponse()
