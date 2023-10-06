package com.markaz.currencyapp.remote


import com.markaz.currencyapp.di.network.CurrencyApi
import com.markaz.currencyapp.di.network.CurrencyRetroApi
import com.markaz.currencyapp.dto.apidto.CurrencyRateResponse
import com.markaz.currencyapp.local.entities.CurrencyEntity
import com.markaz.currencyapp.remote.erros.ApiError
import com.markaz.currencyapp.dto.apidto.CurrencyResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


@ActivityRetainedScoped
class CurrencyRepo @Inject constructor(private val remoteDataSource: CurrencyRetroApi) : BaseRepository(),
    CurrencyApi {
    override suspend fun getAllCurrencies(url: String): CurrencyResult {
        val response = executeSafelyRaw(call = {
            remoteDataSource.getAllCurrencies(url)
        })
        return if (response?.isSuccessful == true && response.body()!= null) {
            CurrencyResult.Success(convertToCurrencyList(response.body()))
        } else {
            CurrencyResult.Error(
                error = ApiError(
                    statusCode = response?.code() ?: -1,
                    message = response?.message() ?: ""
                )
            )
        }
    }

    override suspend fun getLatestCurrencyRates(
        url: String,
        appId: String
    ): ApiResponse<CurrencyRateResponse> {

      return executeSafely(call = {
          remoteDataSource.getLatestCurrencyRates(
               url =url, appId = appId
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