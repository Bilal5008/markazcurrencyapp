package com.markaz.currencyapp.remote

import com.markaz.currencyapp.base.network.CurrencyApiService
import com.markaz.currencyapp.dto.responsedtos.CurrencyResponse
import com.task.currencyapp.data.remote.baseclient.erros.ApiError
import retrofit2.Response
import javax.inject.Inject


interface CurrencyRepo {
    suspend fun getCurrencyResponse(): ApiResponse<CurrencyResponse>
}

class CurrencyRepoImpl @Inject constructor(val apiService: CurrencyApiService) : BaseRepository(),
    CurrencyRepo {

    override suspend fun getCurrencyResponse(): ApiResponse<CurrencyResponse> {
        val response = executeSafelyRaw(call = {
            apiService.getAllCurrencies()
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