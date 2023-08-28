package com.katerinavp.currencies_screen_impl.di;

import com.katerinavp.currencies_screen_impl.fragments.CurrencyFragment;
import dagger.Subcomponent

@Subcomponent
interface CurrencyFragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CurrencyFragmentComponent
    }

    fun inject(fragment:CurrencyFragment)
}

