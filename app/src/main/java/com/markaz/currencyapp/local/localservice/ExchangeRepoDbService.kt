package com.markaz.currencyapp.local.localservice

import com.markaz.currencyapp.local.entities.CurrencyEntity
import com.markaz.currencyapp.local.entities.ExchangeRateEntity
import kotlinx.coroutines.flow.Flow


interface ExchangeRepoDbService {
     var getCurrencies: Flow<List<CurrencyEntity>>?
     fun insertCurrencies(currencyList: List<CurrencyEntity>)

     var getCurrencyRates: Flow<List<ExchangeRateEntity>>?
     fun insertCurrencyRates(rates: List<ExchangeRateEntity>)
}