package com.markaz.currencyapp.local.localservice

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.markaz.currencyapp.dto.responsedtos.CurrencyRateResponse
import com.markaz.currencyapp.local.entities.CurrencyEntity
import com.markaz.currencyapp.local.entities.ExchangeRateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExchangeLocalDao {
    @Query("SELECT * FROM currency")
     fun getAllCurrencies(): Flow<List<CurrencyEntity>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAllCurrencies(currencies: List<CurrencyEntity>)

    @Query("SELECT * FROM currency_rates")
     fun getAllCurrencyRates():  Flow<List<ExchangeRateEntity>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAllCurrencyRates(exchangeRates: List<ExchangeRateEntity>?)
}