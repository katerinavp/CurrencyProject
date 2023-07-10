package com.katerinavp.currency.di.modules

import androidx.lifecycle.ViewModel
import com.katerinavp.currency.di.ViewModelKey
import com.katerinavp.currency.viewmodel.ConverterViewModel
import com.katerinavp.currency.viewmodel.CurrencyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Инициализация различных ViewModel
 */
@Suppress("unused")
@Module
abstract class ViewModelModule : ViewModelModuleBase() {

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    abstract fun bindCurrencyViewModel(viewModel: CurrencyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ConverterViewModel::class)
    abstract fun bindConvertViewModel(viewModel: ConverterViewModel): ViewModel


}