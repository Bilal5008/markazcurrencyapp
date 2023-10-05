package com.markaz.currencyapp.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyEntity(
    @PrimaryKey
    val currencyId: String,
    @ColumnInfo(name = "currencyName")
    val currencyName: String
)