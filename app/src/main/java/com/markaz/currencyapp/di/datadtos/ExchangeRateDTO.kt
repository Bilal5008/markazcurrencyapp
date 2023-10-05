package com.markaz.currencyapp.di.datadtos

import com.markaz.currencyapp.remote.BaseDataResponse
import com.task.currencyapp.domain.datadtos.ExchangeRate


data class ExchangeRateDTO(
    val base: String,
    val rateList: List<ExchangeRate>
) : BaseDataResponse()
