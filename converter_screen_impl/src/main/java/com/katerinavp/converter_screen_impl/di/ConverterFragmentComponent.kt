package com.katerinavp.converter_screen_impl.di

import com.katerinavp.converter_screen_impl.fragments.ConverterFragment
import dagger.Subcomponent


@Subcomponent
interface ConverterFragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ConverterFragmentComponent
    }

    fun inject(fragment: ConverterFragment)
}

