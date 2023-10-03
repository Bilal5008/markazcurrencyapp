package com.markaz.currencyapp.dto.responsedtos

import com.google.gson.annotations.SerializedName
import com.markaz.currencyapp.dto.BaseApiResponse

data class CurrencyResponse(
    @SerializedName("currencies")
    val currencies: Map<String, String>? = null
): BaseApiResponse()
