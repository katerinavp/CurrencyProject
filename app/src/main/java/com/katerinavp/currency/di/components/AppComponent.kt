package com.katerinavp.currency.di.components

import android.content.Context
import com.katerinavp.currency.App
import com.katerinavp.currency.db.di.DbModule
import com.katerinavp.currency.di.modules.CurrencyModule
import com.katerinavp.currency.di.modules.NetworkModule
import com.katerinavp.currency.di.modules.ViewModelModule
import com.katerinavp.currency.view.fragments.base.InitFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Основной компонент dagger 2 для инъекции зависимостей
 */

@Singleton
@Component(modules = [NetworkModule::class, CurrencyModule::class,  ViewModelModule::class, DbModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(fragment: InitFragment)

    fun inject(app: App)
}

