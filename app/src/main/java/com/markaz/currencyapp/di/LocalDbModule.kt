package com.markaz.currencyapp.di

import android.content.Context
import androidx.room.Room
import com.markaz.currencyapp.local.db.ExchangeAppDB
import com.markaz.currencyapp.local.localservice.CurrencyLocalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDbModule {
    @Provides
    fun provideExchangeDao(appDatabase: ExchangeAppDB): CurrencyLocalDao {
        return appDatabase.exchangeLocalDao()
    }

    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext appContext: Context): ExchangeAppDB {
        return Room.databaseBuilder(
            appContext,
            ExchangeAppDB::class.java,
            "ExchangeAppDB"
        ).build()
    }

}