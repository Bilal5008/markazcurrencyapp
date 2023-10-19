package com.markaz.currencyapp.network.repo


import androidx.lifecycle.viewModelScope
import com.markaz.currencyapp.di.network.CurrencyApi
import com.markaz.currencyapp.di.network.CurrencyRetroApi
import com.markaz.currencyapp.dto.apidto.CurrencyRateResponse
import com.markaz.currencyapp.local.entities.CurrencyEntity
import com.markaz.currencyapp.network.erros.ApiError
import com.markaz.currencyapp.dto.apidto.CurrencyResult
import com.markaz.currencyapp.local.localservice.CurrencyLocalDao
import com.markaz.currencyapp.network.base.ApiResponse
import com.markaz.currencyapp.network.base.BaseRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@ActivityRetainedScoped
class CurrencyRepo @Inject constructor(
    private val remoteDataSource: CurrencyRetroApi,
    private val exchangeLocalDao: CurrencyLocalDao
) : BaseRepository(),
    CurrencyApi {
    override suspend fun getAllCurrencies(url: String): CurrencyResult {
        val localCurrencyList = exchangeLocalDao.getAllCurrencies()
        if (localCurrencyList.isEmpty()) {
            val response = executeSafelyRaw(call = {
                remoteDataSource.getAllCurrencies(url)
            })
            return if (response?.isSuccessful == true && response.body() != null) {
                val success = convertToCurrencyList(response.body())
                withContext(Dispatchers.IO)
                {
                    insertCurrencies(success)
                }
                CurrencyResult.Success(success)
            } else {
                CurrencyResult.Error(
                    error = ApiError(
                        statusCode = response?.code() ?: -1,
                        message = response?.message() ?: ""
                    )
                )
            }

        } else {

            return CurrencyResult.Success(localCurrencyList)
        }


    }

    private fun insertCurrencies(response: List<CurrencyEntity>) {

        exchangeLocalDao.insertAllCurrencies(response)

    }

    override suspend fun getLatestCurrencyRates(
        url: String,
        appId: String
    ): ApiResponse<CurrencyRateResponse> {

        return executeSafely(call = {
            remoteDataSource.getLatestCurrencyRates(
                url = url, appId = appId
            )
        })

    }

    companion object {
        const val ALL_CURRENCIES_ENDPOINT = "api/currencies.json"
        const val LATEST_RATES_ENDPOINT = "api/latest.json"
    }

    private fun convertToCurrencyList(currenciesMap: Map<String, String>?): List<CurrencyEntity> {
        return currenciesMap?.map { (code, name) ->
            CurrencyEntity(currencyId = code, currencyName = name)
        } ?: emptyList()
    }


}