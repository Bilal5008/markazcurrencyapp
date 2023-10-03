package com.markaz.currencyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.markaz.currencyapp.base.Resource
import com.markaz.currencyapp.dto.responsedtos.CurrencyResponse
import com.markaz.currencyapp.remote.CurrencyRepoImpl
import com.markaz.currencyapp.remote.CurrencyRepoImpl.Companion.URL_GET_CURRENCIES
import com.markaz.currencyapp.utils.Coroutines
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(private val newProductPagesRepoImpl: CurrencyRepoImpl) : ViewModel() {

    val currencyLiveData = MutableLiveData<Resource<CurrencyResponse>>()
    fun getCurrencyPageData() = Coroutines.default(this) {
        currencyLiveData.postValue(Resource.Loading())
        newProductPagesRepoImpl.getCurrencyResponse("https://openexchangerates.org/$URL_GET_CURRENCIES")
    }

}