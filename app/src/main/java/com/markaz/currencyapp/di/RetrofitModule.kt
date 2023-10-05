package com.markaz.currencyapp.di


import android.app.Application
import android.content.Context
import com.markaz.currencyapp.Config
import com.markaz.currencyapp.di.application.MarkazCurrencyApplication
import com.markaz.currencyapp.utils.Coroutines
import com.markaz.currencyapp.utils.Utility.showToast
import com.markaz.currencyapp.di.interceptor.ConnectivityInterceptor
import com.markaz.currencyapp.di.network.CurrencyRetroApi
import com.markaz.currencyapp.di.network.RetroNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    fun providesGitRepoRetroService(): CurrencyRetroApi =  RetroNetwork().createService(CurrencyRetroApi::class.java)
}