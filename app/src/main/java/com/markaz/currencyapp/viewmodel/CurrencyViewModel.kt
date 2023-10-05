package com.markaz.currencyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markaz.currencyapp.dto.responsedtos.CurrencyResponse
import com.markaz.currencyapp.remote.ApiResponse
import com.markaz.currencyapp.remote.CurrencyRepoImpl
import com.markaz.currencyapp.remote.CurrencyRepoImpl.Companion.URL_GET_CURRENCIES
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(private val currencyRepoImpl: CurrencyRepoImpl) :
    ViewModel() {

    private val _responseStateFlow = MutableStateFlow<ApiResponse<CurrencyResponse>>(ApiResponse.Loading)

    val responseStateFlow: StateFlow<ApiResponse<CurrencyResponse>>
        get() = _responseStateFlow

    fun getCurrencyPageData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response =
                currencyRepoImpl.getCurrencyResponse(URL_GET_CURRENCIES)
            _responseStateFlow.emit(response)
        }
    }
}