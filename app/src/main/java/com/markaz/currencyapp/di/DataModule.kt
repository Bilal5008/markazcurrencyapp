package com.markaz.currencyapp.di

import com.markaz.currencyapp.di.network.CurrencyApi
import com.markaz.currencyapp.local.localservice.ExchangeRepoDbService
import com.markaz.currencyapp.local.localservice.ExchangeRepoLocal
import com.markaz.currencyapp.remote.CurrencyRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun providesUserApi(retrofit: CurrencyRepo): CurrencyApi

    @Binds
    @Singleton
    abstract fun provideExchangeLocalRepository(exchangeRepoLocal: ExchangeRepoLocal): ExchangeRepoDbService

}