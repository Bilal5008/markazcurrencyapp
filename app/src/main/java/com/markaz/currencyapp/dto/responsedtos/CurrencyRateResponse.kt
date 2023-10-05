package com.markaz.currencyapp.dto.responsedtos

import com.google.gson.annotations.SerializedName
import com.markaz.currencyapp.remote.BaseApiResponse

data class CurrencyRateResponse(
    @SerializedName("base")
    val base: String? = null,
    @SerializedName("rates")
    val rates: Map<String, Double>? = null
) : BaseApiResponse()
