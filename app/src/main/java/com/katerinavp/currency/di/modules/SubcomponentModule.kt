package com.katerinavp.currency.di.modules

import com.katerinavp.converter_screen_impl.di.ConverterFragmentComponent
import com.katerinavp.currencies_screen_impl.di.CurrencyFragmentComponent
import com.katerinavp.favorites_screen_impl.di.FavoritesFragmentComponent
import dagger.Module

@Module(subcomponents = [CurrencyFragmentComponent::class, ConverterFragmentComponent::class, FavoritesFragmentComponent::class])
interface SubcomponentModule
