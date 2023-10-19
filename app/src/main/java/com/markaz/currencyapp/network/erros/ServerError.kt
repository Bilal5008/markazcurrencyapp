package com.markaz.currencyapp.network.erros

data class ServerError(
    val code: Int?,
    val message: String?,
    val actualCode: String = "-1"
)
