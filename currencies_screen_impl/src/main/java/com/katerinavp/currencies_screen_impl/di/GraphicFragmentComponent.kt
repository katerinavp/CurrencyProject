package com.katerinavp.currencies_screen_impl.di

import com.katerinavp.currencies_screen_impl.fragments.GraphicFragment
import dagger.Subcomponent

@Subcomponent
interface GraphicFragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): GraphicFragmentComponent
    }

    fun inject(fragment: GraphicFragment)
}

