package com.markaz.currencyapp.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_rates")
data class ExchangeRateEntity(
    @PrimaryKey
    @ColumnInfo(name = "currencyId")
    val currencyId: String,
    @ColumnInfo(name = "currencyRate")
    val currencyRate: Double,
    @ColumnInfo(name = "selectedCurrency")
    val selectedCurrency: String
)