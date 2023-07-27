package com.katerinavp.currency.view.fragments.base

import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.AppBarLayout
import com.katerinavp.currency.App
import com.katerinavp.currency.MainActivity
import com.katerinavp.currency.R
import com.katerinavp.currency.di.components.AppComponent
import javax.inject.Inject
import javax.inject.Named

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

    /**
     * Обновление Toolbar
     */
    protected fun updateToolbar(appBar: AppBarLayout?, title: String) {
        val toolbar = appBar?.findViewById<Toolbar>(R.id.toolbar)
        appBar?.bringToFront()
//        getMainActivity().updateDrawer(toolbar)
        updateTitle(appBar, title)
    }

//    protected fun getMainActivity(): MainActivity {
//        return requireActivity() as MainActivity
//    }

    open fun updateTitle(appBar: AppBarLayout?, title: String) {
        val textView = appBar?.findViewById<TextView>(R.id.toolTitle)
        textView?.text = title
    }

}