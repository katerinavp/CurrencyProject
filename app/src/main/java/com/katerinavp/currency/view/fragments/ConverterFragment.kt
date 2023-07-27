package com.katerinavp.currency.view.fragments

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.katerinavp.currency.common.extension.convertTo
import com.katerinavp.currency.common.extension.returnCharCode
import com.katerinavp.currency.databinding.FragmentConverterBinding
import com.katerinavp.currency.model.data.ModelResponseNetwork
import com.katerinavp.currency.view.fragments.base.InitFragment
import com.katerinavp.currency.viewmodel.ConverterViewModel
import kotlinx.coroutines.launch

class ConverterFragment : InitFragment() {
    private val binding by lazy { FragmentConverterBinding.inflate(layoutInflater) }

    private val viewModel by lazy { getViewModel<ConverterViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        initFragment()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbar(binding.appBar.appBar, getString(com.katerinavp.currency.R.string.convert))

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.converterState.collect(::updateStateCurrency)
                }
            }
        }

    }

    private fun updateStateCurrency(state: CurrencyState) {
        when (state) {
            is CurrencyState.Empty -> {}
            is CurrencyState.Success -> {
                updateConverter(state.data)
            }

            is CurrencyState.SumResult -> {
                updateConvertResult(state.data)
            }

            is CurrencyState.Error -> {}

        }
    }

    private fun updateConverter(data: ModelResponseNetwork) {

        // Create an ArrayAdapter using a simple spinner layout and car models array

        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.simple_spinner_item, data.returnCharCode())


        // Set layout to use when the spinner with the list is displayed

        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        // Set Adapter to Spinner
        binding.currencyChooseSpinner.adapter = arrayAdapter


        binding.currencyEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != "") { // let нет работает

                    binding.currencyEditText.text.trim().toString().also {
                        viewModel.convert(
                            it.toInt(),
                            data.convertTo()[(binding.currencyChooseSpinner.selectedItemPosition)]
                        )

                    }

                } else {
                    viewModel.convert(
                        0,
                        data.convertTo()[(binding.currencyChooseSpinner.selectedItemPosition)]
                    )
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.currencyChooseSpinner.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {

                        override fun onItemSelected(

                            parent: AdapterView<*>?,

                            view: View?,

                            position: Int,

                            id: Long

                        ) {
                            if (s.toString() != "") {
                                binding.currencyEditText.text.trim().toString().also {
                                    viewModel.convert(
                                        it.toInt(),
                                        data.convertTo()[position]
                                    )
                                }

                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
//
                            //no activity or action when nothing is selected
                        }
                    }

            }

        })
    }

    private fun updateConvertResult(data: String) {
        binding.currencyResultEditText.setText(data)
    }
}