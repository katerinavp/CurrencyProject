package com.katerinavp.currencies_screen_impl.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.katerinavp.core.ResponseState
import com.katerinavp.core.extension.convertDateToString
import com.katerinavp.currencies_screen_impl.R
import com.katerinavp.currencies_screen_impl.adapters.AdapterCurrency
import com.katerinavp.currencies_screen_impl.databinding.FragmentCurrencyBinding
import com.katerinavp.currencies_screen_impl.di.CurrencyComponentProvider
import com.katerinavp.currencies_screen_impl.di.CurrencyFragmentComponent
import com.katerinavp.currencies_screen_impl.viewmodel.CurrencyViewModel
import com.katerinavp.currency_api.model.CurrencyDomainModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyFragment : Fragment() {
    private val binding by lazy { FragmentCurrencyBinding.inflate(layoutInflater) }
    private val viewModel: CurrencyViewModel by viewModels { viewModelFactory }

    var currencyFragmentComponent: CurrencyFragmentComponent? = null

    @Inject
    lateinit var viewModelFactory: CurrencyViewModel.Factory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        initFragment()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbar(binding.appBar.appBar, getString(com.katerinavp.currency_api.R.string.currency))

        binding.appBar.searchLayout.setTransition(R.id.start, R.id.end)
        binding.appBar.searchLayout.setTransitionDuration(MOTION_DURATION)

//        binding.btnConvert.isEnabled = false
//        binding.btnUpdate.isEnabled = false

        currencyFragmentComponent = (requireContext().applicationContext as CurrencyComponentProvider)
            .provideCurrencyFragmentComponent()

        currencyFragmentComponent?.inject(this)


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.currencyState.collect(::updateStateCurrency)
                }
            }
        }

    }

    private fun updateToolbar(appBar: AppBarLayout?, title: String) {
        val toolbar = appBar?.findViewById<Toolbar>(R.id.toolbar)
        appBar?.bringToFront()
//        getMainActivity().updateDrawer(toolbar)
        updateTitle(appBar, title)
    }

    private fun updateTitle(appBar: AppBarLayout?, title: String) {
        val textView = appBar?.findViewById<TextView>(R.id.toolTitle)
        textView?.text = title
    }

    private fun updateStateCurrency(state: ResponseState<List<CurrencyDomainModel>>) {
        when (state) {
            is ResponseState.Empty -> {
                binding.progressBar.visibility = View.VISIBLE
            }

            is ResponseState.Success -> {
                updateCurrency(state.data)
            }

            is ResponseState.Error -> {}
        }
    }

    private fun updateCurrency(data: List<CurrencyDomainModel>) {
        binding.date.text = data.first().date.convertDateToString()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = AdapterCurrency()
        binding.recyclerView.adapter = adapter

        adapter.submitList(data)
//        binding.btnConvert.isEnabled = true

//        binding.btnConvert.setOnClickListener {
//            findNavController().navigate(R.id.converterFragment)
//        }
    }

    companion object {
        private const val MOTION_DURATION = 200
        private const val DEFAULT_SENDER_TIMEOUT = 500L
    }

}