package com.markaz.currencyapp.local.localservice

import com.markaz.currencyapp.dto.Currency
import com.markaz.currencyapp.local.entities.CurrencyEntity
import kotlinx.coroutines.flow.Flow


interface ExchangeRepoDbService {
     var getCurrencies: Flow<List<CurrencyEntity>>?
     fun insertCurrencies(currencyList: List<CurrencyEntity>)


}