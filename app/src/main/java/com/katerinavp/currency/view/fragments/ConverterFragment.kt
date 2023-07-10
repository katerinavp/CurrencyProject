package com.katerinavp.currency.view.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.converterState.collect(::updateStateCurrency)
                }
            }
        }
    }

    private fun updateStateCurrency(state: ConverterState) {
        when (state) {
            is ConverterState.Empty -> {}
            is ConverterState.Success -> {
                updateConverter(state.data)
            }

            is ConverterState.Error -> {}

        }
    }

    private fun updateConverter(data: ModelResponseNetwork) {

        // Create an ArrayAdapter using a simple spinner layout and car models array

        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.simple_spinner_item, data.returnCharCode())

        // Set layout to use when the spinner with the list is displayed

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set Adapter to Spinner

        binding.currencySpinner.adapter = arrayAdapter



        binding.currencySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(

                    parent: AdapterView<*>?,

                    view: View?,

                    position: Int,

                    id: Long

                ) {

//                Toast.makeText(this@MainActivity, carmodels[position], Toast.LENGTH_LONG)
//
//                    .show()
//
//                textview!!.text = "The selected car model is : " + carmodels[position]

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                    //no activity or action when nothing is selected

                }
            }
    }
}