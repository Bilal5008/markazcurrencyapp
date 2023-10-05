package com.markaz.currencyapp.dto.responsedtos

import com.google.gson.annotations.SerializedName
import com.markaz.currencyapp.remote.BaseApiResponse

data class CurrencyResponse(
    @SerializedName("currencies")
    val currencies: Map<String, String>? = null
): BaseApiResponse()
