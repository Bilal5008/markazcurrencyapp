package com.markaz.currencyapp.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.markaz.currencyapp.local.converter.DataConverter
import com.markaz.currencyapp.local.entities.CurrencyEntity
import com.markaz.currencyapp.local.entities.ExchangeRateEntity
import com.markaz.currencyapp.local.localservice.CurrencyLocalDao

@Database(
    entities = [CurrencyEntity::class, ExchangeRateEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class ExchangeAppDB : RoomDatabase() {
    abstract fun exchangeLocalDao(): CurrencyLocalDao
}