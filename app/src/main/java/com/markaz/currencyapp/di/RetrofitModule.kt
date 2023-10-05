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
    fun providesMainApplicationInstance(@ApplicationContext context: Context): MarkazCurrencyApplication {
        return context as MarkazCurrencyApplication
    }

    @Singleton
    @Provides
    fun provideLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Singleton
    @Provides
    fun provideOKHTTPClient(application: MarkazCurrencyApplication, httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .cache(cache)
        if (true) {
            httpClient.addInterceptor(httpLoggingInterceptor)
        }
        httpClient.addInterceptor(object : ConnectivityInterceptor() {
            override fun onInternetUnavailable() {
                Coroutines.main {
                    showToast(application,"No Internet Available")
                }
            }

        })
        return httpClient.build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Config.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesGitRepoRetroService(): CurrencyRetroApi =  RetroNetwork().createService(CurrencyRetroApi::class.java)


//    @Singleton
//    @Provides
//    fun providesGitRepoRetroService(retrofit: Retrofit): CurrencyRetroApi {
//        return retrofit.create(CurrencyRetroApi::class.java)
//    }
}