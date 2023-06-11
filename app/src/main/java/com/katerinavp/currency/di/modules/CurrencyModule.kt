package com.katerinavp.currency.di.modules

import androidx.lifecycle.ViewModelProvider
import com.katerinavp.currency.repository.CurrencyRepository
import com.katerinavp.currency.repository.CurrencyRepositoryImpl
import com.katerinavp.currency.viewmodel.CurrencyViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface CurrencyModule {

    @Binds
    fun bindRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository

    @Binds
    fun bindFactory(currencyViewModelFactory: CurrencyViewModelFactory): ViewModelProvider.Factory
}