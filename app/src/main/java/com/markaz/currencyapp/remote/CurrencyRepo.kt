package com.markaz.currencyapp.remote


import com.markaz.currencyapp.di.network.CurrencyApi
import com.markaz.currencyapp.di.network.CurrencyRetroApi
import com.markaz.currencyapp.di.network.RemoteRepo
import com.markaz.currencyapp.dto.responsedtos.CurrencyResponse
import com.markaz.currencyapp.dto.responsedtos.RateResponse
import com.markaz.currencyapp.remote.erros.ApiError
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject


@ActivityRetainedScoped
class CurrencyRepo @Inject constructor(private val remoteDataSource: CurrencyRetroApi) : BaseRepository(),
    CurrencyApi {



    override suspend fun getAllCurrencies(url: String): ApiResponse<CurrencyResponse> {
        val response = executeSafelyRaw(call = {
            remoteDataSource.getAllCurrencies(url)
        })
        return if (response?.isSuccessful == true) {
            ApiResponse.Success(response.code(), CurrencyResponse(currencies = response.body()))
        } else {
            ApiResponse.Error(
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
    ): ApiResponse<CurrencyResponse> {
        TODO("Not yet implemented")
    }

    companion object {
        const val ALL_CURRENCIES_ENDPOINT = "api/currencies.json"
        const val LATEST_RATES_ENDPOINT = "api/latest.json"
    }


}