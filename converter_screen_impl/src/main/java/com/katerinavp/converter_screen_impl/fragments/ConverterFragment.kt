package com.katerinavp.converter_screen_impl.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.katerinavp.converter_screen_impl.databinding.FragmentConverterBinding
import com.katerinavp.converter_screen_impl.di.ConverterComponentProvider
import com.katerinavp.converter_screen_impl.di.ConverterFragmentComponent
import com.katerinavp.converter_screen_impl.viewmodel.ConverterViewModel
import com.katerinavp.core.ResponseState
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConverterFragment : Fragment() {
    private val binding by lazy { FragmentConverterBinding.inflate(layoutInflater) }
    private val viewModel: ConverterViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: ConverterViewModel.Factory
    var converterFragmentComponent: ConverterFragmentComponent? = null

    private val arrayAdapter by lazy {
        ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            mutableListOf<String>()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        updateToolbar(binding.appBar.appBar, getString(R.string.convert))
        converterFragmentComponent =
            (requireContext().applicationContext as ConverterComponentProvider)
                .provideConverterFragmentComponent()

        converterFragmentComponent?.inject(this)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.converterState.collect(::updateStateCurrency)
                }
            }
        }

        binding.currencyChooseSpinner.adapter = arrayAdapter

        binding.currencyEditText.addTextChangedListener {
            viewModel.setInput(binding.currencyEditText.text.toString())
        }

        binding.currencyChooseSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.setSelectedCurrency(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
    }

    private fun updateStateCurrency(state: ResponseState<UiState>) {
        when (state) {
            is ResponseState.Empty -> {}
            is ResponseState.Success -> {
                updateConverter(state.data)
                updateConvertResult(state.data.result)
            }

            is ResponseState.Error -> {}

        }
    }

    private fun updateConverter(data: UiState) {

        arrayAdapter.clear()
        arrayAdapter.addAll(data.currencies.map { it.code })

        binding.currencyChooseSpinner.setSelection(data.selectedCurrency ?: 0)

    }

    private fun updateConvertResult(data: String) {
        binding.currencyResultTextView.text = data

    }
}