package com.markaz.currencyapp.di.datadtos

import com.markaz.currencyapp.remote.BaseDataResponse


data class ExchangeRateDTO(
    val base: String,
    val rateList: List<ExchangeRate>
) : BaseDataResponse()
