package com.markaz.currencyapp.local.localservice

import com.markaz.currencyapp.dto.Currency
import com.markaz.currencyapp.local.entities.CurrencyEntity
import kotlinx.coroutines.flow.Flow


import javax.inject.Inject

class ExchangeRepoLocal @Inject constructor(private val exchangeLocalDao: ExchangeLocalDao) : ExchangeRepoDbService {


   //    override suspend fun insertCurrencies(currencies: List<CurrencyEntity>) =
//        exchangeLocalDao.insertAllCurrencies(currencies)
//
//    override suspend fun getExchangeRates(): List<ExchangeRateEntity>? =
//        exchangeLocalDao.getAllRates()
//
//    override suspend fun insertExchangeRates(rates: List<ExchangeRateEntity>) =
//        exchangeLocalDao.insertRates(rates)
   override var getCurrencies: Flow<List<CurrencyEntity>>? = exchangeLocalDao.getAllCurrencies()!!

   override fun insertCurrencies(currencyList: List<CurrencyEntity>) {
      exchangeLocalDao.insertAllCurrencies(currencyList)
   }


}