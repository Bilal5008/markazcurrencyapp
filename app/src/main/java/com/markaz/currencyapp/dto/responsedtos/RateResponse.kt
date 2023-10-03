package com.markaz.currencyapp.dto.responsedtos

import com.google.gson.annotations.SerializedName
import com.markaz.currencyapp.dto.BaseApiResponse

data class RateResponse(
    @SerializedName("base")
    val base: String? = null,
    @SerializedName("rates")
    val rates: Map<String, Double>? = null
) : BaseApiResponse()
