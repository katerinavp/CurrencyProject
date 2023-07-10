package com.katerinavp.currency.di.modules

import com.katerinavp.currency.repository.CurrencyRepository
import com.katerinavp.currency.repository.CurrencyRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface CurrencyModule {

    @Binds
    fun bindRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository
}