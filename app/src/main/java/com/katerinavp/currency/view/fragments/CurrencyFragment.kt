package com.katerinavp.currency.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.katerinavp.currency.R
import com.katerinavp.currency.databinding.FragmentCurrencyBinding
import com.katerinavp.currency.view.adapters.AdapterCurrency
import com.katerinavp.currency.view.fragments.base.InitFragment
import com.katerinavp.currency.viewmodel.CurrencyViewModel
import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_impl.repository.extension.convertDateToString
import kotlinx.coroutines.launch

class CurrencyFragment : InitFragment() {
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
        updateToolbar(binding.appBar.appBar, getString(R.string.currency))

        binding.appBar.searchLayout.setTransition(R.id.start, R.id.end)
        binding.appBar.searchLayout.setTransitionDuration(MOTION_DURATION)

//        binding.btnConvert.isEnabled = false
//        binding.btnUpdate.isEnabled = false

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.currencyState.collect(::updateStateCurrency)
                }
            }
        }

    }

    private fun updateStateCurrency(state: CurrencyState) {
        when (state) {
            is CurrencyState.Empty -> {
                binding.progressBar.visibility = View.VISIBLE
            }

            is CurrencyState.Success -> {
                updateCurrency(state.data)
            }

            is CurrencyState.Error -> {}
            is CurrencyState.SumResult -> {

            }
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