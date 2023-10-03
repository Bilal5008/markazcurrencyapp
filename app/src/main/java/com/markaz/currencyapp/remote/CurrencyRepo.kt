package com.markaz.currencyapp.remote


import com.markaz.currencyapp.base.network.RemoteDataSource
import com.markaz.currencyapp.dto.responsedtos.CurrencyResponse
import com.task.currencyapp.data.remote.baseclient.erros.ApiError
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


interface CurrencyRepo {
    suspend fun getCurrencyResponse(url: String): ApiResponse<CurrencyResponse>
}

@ActivityRetainedScoped
class CurrencyRepoImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : BaseRepository(),
    CurrencyRepo {

    override suspend fun getCurrencyResponse(url: String): ApiResponse<CurrencyResponse> {
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



    companion object {
        const val URL_GET_CURRENCIES = "api/currencies.json"
        const val URL_GET_RATES = "api/latest.json"
    }

}