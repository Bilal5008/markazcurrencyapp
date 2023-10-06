package com.markaz.currencyapp.di


import com.markaz.currencyapp.di.network.CurrencyRetroApi
import com.markaz.currencyapp.di.network.RetroNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    fun providesGitRepoRetroService(): CurrencyRetroApi =  RetroNetwork().createService(CurrencyRetroApi::class.java)
}