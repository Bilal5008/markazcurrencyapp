package com.markaz.currencyapp.local.localservice

import com.markaz.currencyapp.local.entities.CurrencyEntity
import com.markaz.currencyapp.local.entities.ExchangeRateEntity
import kotlinx.coroutines.flow.Flow


import javax.inject.Inject

class CurrencyRepoLocal @Inject constructor(private val exchangeLocalDao: CurrencyLocalDao) : ExchangeRepoDbService {

    override var getCurrencies: Flow<List<CurrencyEntity>>? = exchangeLocalDao.getAllCurrenciesFlow()!!

    override fun insertCurrencies(currencyList: List<CurrencyEntity>) {
        exchangeLocalDao.insertAllCurrencies(currencyList)
    }

    override var getCurrencyRates: Flow<List<ExchangeRateEntity>>? =
        exchangeLocalDao.getAllCurrencyRates()!!

    override fun insertCurrencyRates(rates: List<ExchangeRateEntity>) {
        exchangeLocalDao.insertAllCurrencyRates(rates)
    }


}