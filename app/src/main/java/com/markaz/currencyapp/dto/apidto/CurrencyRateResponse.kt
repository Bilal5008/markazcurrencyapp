package com.markaz.currencyapp.dto.apidto

import com.google.gson.annotations.SerializedName
import com.markaz.currencyapp.network.base.BaseApiResponse

data class CurrencyRateResponse(
    @SerializedName("base")
    val base: String? = null,
    @SerializedName("rates")
    val rates: Map<String, Double>? = null
) : BaseApiResponse()
