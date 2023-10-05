package com.markaz.currencyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markaz.currencyapp.dto.Currency
import com.markaz.currencyapp.dto.responsedtos.CurrencyRateResponse
import com.markaz.currencyapp.dto.responsedtos.CurrencyResponse
import com.markaz.currencyapp.local.localservice.ExchangeRepoLocal
import com.markaz.currencyapp.remote.ApiResponse
import com.markaz.currencyapp.remote.CurrencyRepo
import com.markaz.currencyapp.remote.CurrencyRepo.Companion.ALL_CURRENCIES_ENDPOINT
import com.markaz.currencyapp.local.entities.CurrencyEntity
import com.markaz.currencyapp.local.entities.ExchangeRateEntity
import com.markaz.currencyapp.remote.CurrencyRepo.Companion.LATEST_RATES_ENDPOINT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyRepoImpl: CurrencyRepo,
    private val exchangeRepoLocal: ExchangeRepoLocal
) :
    ViewModel() {

    private val _responseCurrencyStateFlow =
        MutableStateFlow<ApiResponse<CurrencyResponse>>(ApiResponse.Loading)

    val responseCurrencyStateFlow: StateFlow<ApiResponse<CurrencyResponse>>
        get() = _responseCurrencyStateFlow


    private val _responseCurrencyRatesStateFlow =
        MutableStateFlow<ApiResponse<CurrencyRateResponse>>(ApiResponse.Loading)

    val responseCurrencyRatesStateFlow: StateFlow<ApiResponse<CurrencyRateResponse>>
        get() = _responseCurrencyRatesStateFlow


    private val _items = MutableStateFlow<List<CurrencyEntity>>(emptyList())
    val items: StateFlow<List<CurrencyEntity>> get() = _items


    fun getCurrencyPageData() {
        viewModelScope.launch(Dispatchers.IO) {
            exchangeRepoLocal.getCurrencies?.collect { currencies ->
                if (currencies.isNotEmpty()) {
                    val currencyList = currencies.map { entity ->
                        Currency(
                            currencyCode = entity.currencyId,
                            currencyName = entity.currencyName
                        )
                    }
                    _items.emit(currencies)
                } else {
                    val response = currencyRepoImpl.getAllCurrencies(ALL_CURRENCIES_ENDPOINT)
                    if (response is ApiResponse.Success) {
                        insertCurrencies(response.data.currencies)
                    }
                    _responseCurrencyStateFlow.emit(response)
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            exchangeRepoLocal.getCurrencyRates?.collect { currencies ->
                if (currencies.isNotEmpty()) {
                    val currencyList = currencies.map { entity ->
                        ExchangeRateEntity(
                            currencyId = entity.currencyId,
                            currencyRate = entity.currencyRate,
                            selectedCurrency = entity.selectedCurrency

                        )
                    }

                } else {
                    val response =
                        currencyRepoImpl.getLatestCurrencyRates(LATEST_RATES_ENDPOINT, "97e4f7f8ca994ed3828ebaee9fedb32d" )
                    if (response is ApiResponse.Success) {
                        insertCurrenciesRates(response.data.rates)
                    }
                    _responseCurrencyRatesStateFlow.emit(response)
                }
            }
        }

    }

    private fun insertCurrencies(response: Map<String, String>?) {
        viewModelScope.launch(Dispatchers.IO) {
            exchangeRepoLocal.insertCurrencies(convertToCurrencyList(response))
        }


    }

    private fun convertToCurrencyList(currenciesMap: Map<String, String>?): List<CurrencyEntity> {
        return currenciesMap?.map { (code, name) ->
            CurrencyEntity(currencyId = code, currencyName = name)
        } ?: emptyList()
    }

    private fun insertCurrenciesRates(response: Map<String, Double>?) {
        viewModelScope.launch(Dispatchers.IO) {
            exchangeRepoLocal.insertCurrencyRates(convertToCurrencyRateList(response))
        }


    }

    private fun convertToCurrencyRateList(currencyRateMap: Map<String, Double>?): List<ExchangeRateEntity> {
        return currencyRateMap?.map { (code, name) ->
            ExchangeRateEntity(currencyId = code, currencyRate = name, selectedCurrency = "USD")
        } ?: emptyList()
    }


}