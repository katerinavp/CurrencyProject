package com.katerinavp.favorites_screen_impl.di

import com.katerinavp.favorites_screen_impl.fragments.FavoritesFragment
import dagger.Subcomponent

@Subcomponent
interface FavoritesFragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FavoritesFragmentComponent
    }

    fun inject(fragment: FavoritesFragment)
}
