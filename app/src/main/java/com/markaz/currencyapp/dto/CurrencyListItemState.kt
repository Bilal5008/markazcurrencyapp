package com.markaz.currencyapp.dto

data class CurrencyListItemState(
    val currency: Currency,
    var isSelected: Boolean = false
)
