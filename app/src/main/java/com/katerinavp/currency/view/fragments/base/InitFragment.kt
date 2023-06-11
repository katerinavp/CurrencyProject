package com.katerinavp.currency.view.fragments.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.katerinavp.currency.App
import com.katerinavp.currency.di.components.AppComponent
import javax.inject.Inject

abstract class InitFragment: BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    var component: AppComponent? = null

    /**
     * Получение ViewModel
     */
    inline fun <reified T : ViewModel> getViewModel(owner: Fragment? = null): T {
        return ViewModelProvider(owner ?: this, viewModelFactory)[T::class.java]
    }

    protected fun initFragment() {
        if (component == null) {
            component = (activity?.application as App).appComponent
            component?.inject(this)
        }
    }

}