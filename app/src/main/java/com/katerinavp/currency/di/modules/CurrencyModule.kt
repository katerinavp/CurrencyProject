package com.katerinavp.currency.di.modules

import com.katerinavp.currency_api.repository.CurrencyRepository
import com.katerinavp.currency_api.repository.FavoritesRepository
import com.katerinavp.currency_impl.repository.CurrencyRepositoryImpl
import com.katerinavp.currency_impl.repository.FavoritesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface CurrencyModule {

    @Binds
    fun bindRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository

    @Binds
    fun bindFavoritesRepository(favoritesRepositoryImpl: FavoritesRepositoryImpl): FavoritesRepository
}