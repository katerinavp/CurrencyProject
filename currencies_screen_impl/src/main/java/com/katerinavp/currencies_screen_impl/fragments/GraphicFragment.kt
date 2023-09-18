package com.katerinavp.currencies_screen_impl.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.katerinavp.currencies_screen_impl.CurrencyItem
import com.katerinavp.currencies_screen_impl.R
import com.katerinavp.currencies_screen_impl.databinding.FragmentGraphicBinding
import com.katerinavp.currencies_screen_impl.di.CurrencyComponentProvider
import com.katerinavp.currencies_screen_impl.di.GraphicFragmentComponent
import com.katerinavp.currencies_screen_impl.viewmodel.CurrencyViewModel
import javax.inject.Inject

class GraphicFragment : Fragment() {
    private val binding by lazy { FragmentGraphicBinding.inflate(layoutInflater) }

    private var currencyFragmentComponent: GraphicFragmentComponent? = null

    @Inject
    lateinit var viewModelFactory: CurrencyViewModel.Factory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyFragmentComponent = (requireContext().applicationContext as CurrencyComponentProvider)
            .provideGraphicFragmentComponent()

        currencyFragmentComponent?.inject(this)

        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbar.setNavigationOnClickListener {
                findNavController().navigate(R.id.nav_currency)
        }


        binding.linearChart.setData(arrayListOf(
            CurrencyItem (dayNum = 1, amount= 6), CurrencyItem (dayNum = 2, amount= 10),  CurrencyItem (dayNum = 3, amount= 3),

            CurrencyItem (dayNum = 4, amount= 3),CurrencyItem (dayNum = 5, amount= 3),CurrencyItem (dayNum = 6, amount= 3),))

    }

}