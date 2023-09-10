package com.katerinavp.currency

import android.app.Application
import com.katerinavp.converter_screen_impl.di.ConverterComponentProvider
import com.katerinavp.converter_screen_impl.di.ConverterFragmentComponent
import com.katerinavp.currencies_screen_impl.di.CurrencyComponentProvider
import com.katerinavp.favorites_screen_impl.di.FavoritesComponentProvider
import com.katerinavp.currencies_screen_impl.di.CurrencyFragmentComponent
import com.katerinavp.currencies_screen_impl.di.GraphicFragmentComponent
import com.katerinavp.currency.di.components.AppComponent
import com.katerinavp.currency.di.components.DaggerAppComponent
import com.katerinavp.favorites_screen_impl.di.FavoritesFragmentComponent

/**
 * Основной класс приложения
 */
class App : Application(), CurrencyComponentProvider, FavoritesComponentProvider, ConverterComponentProvider {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }

//    val appComponent = DaggerAppComponent.builder().context(this).build()


    override fun provideCurrencyFragmentComponent(): CurrencyFragmentComponent {
        return appComponent.currencyFragmentComponent().create()

    }

    override fun provideConverterFragmentComponent(): ConverterFragmentComponent {
        return appComponent.converterFragmentComponent().create()

    }

    override fun provideGraphicFragmentComponent(): GraphicFragmentComponent {
        return appComponent.graphicFragmentComponent().create()

    }

    override fun provideFavoritesFragmentComponent(): FavoritesFragmentComponent {
        return appComponent.favoritesFragmentComponent().create()
    }

}