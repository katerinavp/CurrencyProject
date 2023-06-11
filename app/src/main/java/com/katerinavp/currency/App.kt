package com.katerinavp.currency

import android.app.Application
import com.katerinavp.currency.di.components.AppComponent
import com.katerinavp.currency.di.components.DaggerAppComponent

/**
 * Основной класс приложения
 */
class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }

}