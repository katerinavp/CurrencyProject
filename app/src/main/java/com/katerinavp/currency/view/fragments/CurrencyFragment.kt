package com.katerinavp.currency.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.katerinavp.currency.R
import com.katerinavp.currency.common.extension.convertTo
import com.katerinavp.currency.databinding.FragmentCurrencyBinding
import com.katerinavp.currency.model.data.ModelResponseNetwork
import com.katerinavp.currency.view.adapters.AdapterCurrency
import com.katerinavp.currency.view.fragments.base.InitFragment
import com.katerinavp.currency.viewmodel.CurrencyViewModel
import kotlinx.coroutines.launch

class CurrencyFragment : InitFragment(){
    private val binding by lazy { FragmentCurrencyBinding.inflate(layoutInflater) }

    private val viewModel by lazy { getViewModel<CurrencyViewModel>() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initFragment()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.currencyState.collect(::updateStateCurrency)
                }
            }
        }

    }

    private fun updateStateCurrency(state: CurrencyState){
        when (state) {
            is CurrencyState.Empty -> {}
            is CurrencyState.Success -> {updateCurrency(state.data)}
            is CurrencyState.Error -> {}

        }
    }

    private fun updateCurrency(data : ModelResponseNetwork){
        binding.inputDate.text = data.timestamp
        val adapter = AdapterCurrency()
        binding.recyclerView.adapter = adapter
        adapter.submitList(data.convertTo())

        binding.btnConvert.setOnClickListener {
            findNavController().navigate(R.id.converterFragment)
        }
    }

}