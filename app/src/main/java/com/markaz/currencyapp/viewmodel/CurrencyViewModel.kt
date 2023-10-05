package com.markaz.currencyapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markaz.currencyapp.dto.Currency
import com.markaz.currencyapp.dto.responsedtos.CurrencyResponse
import com.markaz.currencyapp.local.localservice.ExchangeRepoLocal
import com.markaz.currencyapp.remote.ApiResponse
import com.markaz.currencyapp.remote.CurrencyRepo
import com.markaz.currencyapp.remote.CurrencyRepo.Companion.ALL_CURRENCIES_ENDPOINT
import com.markaz.currencyapp.local.entities.CurrencyEntity
import com.markaz.currencyapp.remote.CurrencyData
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

//    private val _responseStateFlow =
//        MutableStateFlow<ApiResponse<CurrencyResponse>>(ApiResponse.Loading)
//
//    val responseStateFlow: StateFlow<ApiResponse<CurrencyResponse>>
//        get() = _responseStateFlow
//
//    private val _items = MutableStateFlow<List<CurrencyEntity>>(emptyList())
//    val items: StateFlow<List<CurrencyEntity>> get() = _items

    private val _currencyData = MutableStateFlow<CurrencyData>(CurrencyData.Loading)

    val currencyData: StateFlow<CurrencyData> get() = _currencyData


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
                    Log.d("", "Currency$currencyList")
                    _currencyData.value = CurrencyData.LocalData(currencies)
                    _currencyData.emit(_currencyData.value)

                } else {
                    val apiResponse = currencyRepoImpl.getAllCurrencies(ALL_CURRENCIES_ENDPOINT)
                    if (apiResponse is ApiResponse.Success) {
                        _currencyData.value = CurrencyData.ApiSuccess(apiResponse)
                        insertCurrencies(apiResponse.data.currencies)
                    } else if (apiResponse is ApiResponse.Error) {
                        _currencyData.value = CurrencyData.Error(apiResponse.error.message)
                    } else {
                        _currencyData.value = CurrencyData.Loading
                    }
                    _currencyData.emit(_currencyData.value)

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

}