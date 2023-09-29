package com.katerinavp.currency.di;

import com.katerinavp.currency.fragments.CurrencyFragmentTest
import dagger.Subcomponent

@Subcomponent
interface CurrencyFragmentComponentTest {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CurrencyFragmentComponentTest
    }

    fun inject(fragment: CurrencyFragmentTest)
}

