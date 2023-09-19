package com.katerinavp.currency.di.components

import android.content.Context
import com.katerinavp.converter_screen_impl.di.ConverterFragmentComponent
import com.katerinavp.currencies_screen_impl.di.CurrencyFragmentComponent
import com.katerinavp.currencies_screen_impl.di.GraphicFragmentComponent
import com.katerinavp.currency.App
import com.katerinavp.currency.di.modules.DbModule
import com.katerinavp.currency.di.modules.SubcomponentModule
import com.katerinavp.currency.di.modules.CurrencyModule
import com.katerinavp.currency.di.modules.NetworkModule
import com.katerinavp.favorites_screen_impl.di.FavoritesFragmentComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Основной компонент dagger 2 для инъекции зависимостей
 */

@Singleton
@Component(modules = [NetworkModule::class, CurrencyModule::class,  DbModule::class, SubcomponentModule:: class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

    fun currencyFragmentComponent(): CurrencyFragmentComponent.Factory

    fun converterFragmentComponent(): ConverterFragmentComponent.Factory

    fun graphicFragmentComponent(): GraphicFragmentComponent.Factory

    fun favoritesFragmentComponent(): FavoritesFragmentComponent.Factory
}

