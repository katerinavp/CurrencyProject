package com.katerinavp.currency

import com.katerinavp.currency.di.components.AppComponent
import com.katerinavp.currency.di.components.AppComponentTest
import com.katerinavp.currency.di.components.DaggerAppComponentTest


class AppTest : App() {

    val appComponent: AppComponentTest by lazy {
        DaggerAppComponentTest.builder().context(this).build()
    }
}