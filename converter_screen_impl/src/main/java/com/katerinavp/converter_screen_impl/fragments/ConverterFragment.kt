package com.katerinavp.converter_screen_impl.fragments

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        initFragment()
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

    }

//    private fun updateToolbar(appBar: AppBarLayout?, title: String) {
//        val toolbar = appBar?.findViewById<Toolbar>(R.id.toolbar)
//        appBar?.bringToFront()
////        getMainActivity().updateDrawer(toolbar)
//        updateTitle(appBar, title)
//    }
//
//    private fun updateTitle(appBar: AppBarLayout?, title: String) {
//        val textView = appBar?.findViewById<TextView>(R.id.toolTitle)
//        textView?.text = title
//    }

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

        // Create an ArrayAdapter using a simple spinner layout and car models array

        val arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.simple_spinner_item,
                data.currencies.map { it.code })
        binding.currencyChooseSpinner.setSelection(data.selectedCurrency?:0)
        // Set layout to use when the spinner with the list is displayed


        // Set Adapter to Spinner
        binding.currencyChooseSpinner.adapter = arrayAdapter

        binding.currencyEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != "") { // let нет работает

                    binding.currencyEditText.text.trim().toString().also {
                        viewModel.convert(
                            it.toInt(),
                            data.currencies[(binding.currencyChooseSpinner.selectedItemPosition)],
                                    binding.currencyChooseSpinner.selectedItemPosition
                        )

                    }

                } else {
                    viewModel.convert(
                        0,
                        data.currencies[(binding.currencyChooseSpinner.selectedItemPosition)],
                        binding.currencyChooseSpinner.selectedItemPosition
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
//                            if (s.toString() != "") {
//                                binding.currencyEditText.text.trim().toString().also {
//                                    viewModel.convert(
//                                        it.toInt(),
//                                        data.currencies[position],
//                                        position
//                                    )
//                                }
//
//                            }
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
        binding.currencyResultTextView.text = data

    }
}