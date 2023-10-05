package com.markaz.currencyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markaz.currencyapp.dto.responsedtos.CurrencyRateResponse
import com.markaz.currencyapp.local.localservice.ExchangeRepoLocal
import com.markaz.currencyapp.remote.ApiResponse
import com.markaz.currencyapp.remote.CurrencyRepo
import com.markaz.currencyapp.remote.CurrencyRepo.Companion.ALL_CURRENCIES_ENDPOINT
import com.markaz.currencyapp.local.entities.CurrencyEntity
import com.markaz.currencyapp.local.entities.ExchangeRateEntity
import com.markaz.currencyapp.remote.CurrencyRepo.Companion.LATEST_RATES_ENDPOINT
import com.markaz.currencyapp.ui.uilayer.CurrencyResult
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
        MutableStateFlow<CurrencyResult>(CurrencyResult.Loading)

    val responseCurrencyStateFlow: StateFlow<CurrencyResult>
        get() = _responseCurrencyStateFlow


    private val _responseCurrencyRatesStateFlow =
        MutableStateFlow<ApiResponse<CurrencyRateResponse>>(ApiResponse.Loading)

    val responseCurrencyRatesStateFlow: StateFlow<ApiResponse<CurrencyRateResponse>>
        get() = _responseCurrencyRatesStateFlow


    fun getCurrencyPageData() {
        viewModelScope.launch(Dispatchers.IO) {
            exchangeRepoLocal.getCurrencies?.collect { currencies ->
                if (currencies.isNotEmpty()) {
                    _responseCurrencyStateFlow.emit(CurrencyResult.Success(currencies) )
                } else {
                    val response = currencyRepoImpl.getAllCurrencies(ALL_CURRENCIES_ENDPOINT)
                    if (response is CurrencyResult.Success) {
                        insertCurrencies(response.list)
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

    private fun insertCurrencies(response: List<CurrencyEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            exchangeRepoLocal.insertCurrencies(response)
        }


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