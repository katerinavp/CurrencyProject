package com.katerinavp.currency.di.modules

import androidx.lifecycle.ViewModelProvider
import com.katerinavp.currency.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelModuleBase {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}