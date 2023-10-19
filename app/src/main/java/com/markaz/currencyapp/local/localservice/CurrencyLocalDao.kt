package com.markaz.currencyapp.local.localservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.markaz.currencyapp.local.entities.CurrencyEntity
import com.markaz.currencyapp.local.entities.ExchangeRateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyLocalDao {
    @Query("SELECT * FROM currency")
     fun getAllCurrenciesFlow(): Flow<List<CurrencyEntity>>?

    @Query("SELECT * FROM currency")
    fun getAllCurrencies(): List<CurrencyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAllCurrencies(currencies: List<CurrencyEntity>)

    @Query("SELECT * FROM currency_rates")
     fun getAllCurrencyRates():  Flow<List<ExchangeRateEntity>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAllCurrencyRates(exchangeRates: List<ExchangeRateEntity>?)
}