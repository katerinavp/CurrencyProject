package com.katerinavp.currencies_screen_impl.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.katerinavp.currencies_screen_impl.HideKeyboardTouchListener
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

    private var currencyFragmentComponent: CurrencyFragmentComponent? = null
//private lateinit var adapter: AdapterCurrency
    @Inject
    lateinit var viewModelFactory: CurrencyViewModel.Factory

    private val touchListener = HideKeyboardTouchListener()
    private val searchWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.content.removeCallbacks(textSender)
            binding.content.postDelayed(textSender, DEFAULT_SENDER_TIMEOUT)
            if (s.isNullOrEmpty()) {
                binding.appBar.searchLayout.transitionToStart()
            } else {
                binding.appBar.searchLayout.transitionToEnd()
            }
        }
    }

    private val textSender = Runnable {
        if (binding.appBar.search.text.isNullOrEmpty()) {
            viewModel.getCurrency("")
        } else {
            search()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyFragmentComponent = (requireContext().applicationContext as CurrencyComponentProvider)
            .provideCurrencyFragmentComponent()

        currencyFragmentComponent?.inject(this)

        updateToolbar(binding.appBar.appBar, getString(com.katerinavp.currency_api.R.string.currency))

        binding.appBar.searchLayout.setTransition(R.id.start, R.id.end)
        binding.appBar.searchLayout.setTransitionDuration(MOTION_DURATION)

//        binding.btnConvert.isEnabled = false
//        binding.btnUpdate.isEnabled = false

        with(binding.list) {
            layoutManager = LinearLayoutManager(requireContext())
            removeOnItemTouchListener(touchListener)
            addOnItemTouchListener(touchListener)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.currencyState.collect(::updateStateCurrency)
                }
            }
        }

        binding.appBar.cancel.setOnClickListener {
            binding.appBar.search.setText("")
            binding.appBar.search.clearFocus()
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
                binding.progress.visibility = View.VISIBLE
            }

            is ResponseState.Success -> {
                updateCurrency(state.data)
            }

            is ResponseState.Error -> {
                binding.progress.visibility = View.GONE
            }
        }
    }

    private fun updateCurrency(data: List<CurrencyDomainModel>?) {
        data?.let{list ->
            binding.list.visibility = View.VISIBLE
            binding.progress.visibility = View.GONE
            binding.date.text = data.first().date.convertDateToString()
            val adapter = AdapterCurrency()
            binding.list.adapter = adapter

            adapter.submitList(data)
        }

    }

    private fun search() {
        viewModel.getCurrency(binding.appBar.search.text.toString())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.appBar.search.removeTextChangedListener(searchWatcher)
        binding.appBar.search.addTextChangedListener(searchWatcher)
    }

    override fun onStop() {
        super.onStop()
        binding.appBar.search.clearFocus()
        binding.content.removeCallbacks(textSender)
    }

    companion object {
        private const val MOTION_DURATION = 200
        private const val DEFAULT_SENDER_TIMEOUT = 500L
    }

}