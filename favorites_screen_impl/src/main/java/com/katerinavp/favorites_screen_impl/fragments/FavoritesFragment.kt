package com.katerinavp.favorites_screen_impl.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.katerinavp.core.ResponseState
import com.katerinavp.core.extension.convertDateToString
import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.favorites_screen.databinding.FragmentFavoritesBinding
import com.katerinavp.favorites_screen_impl.adapters.AdapterFavorites
import com.katerinavp.favorites_screen_impl.di.FavoritesComponentProvider
import com.katerinavp.favorites_screen_impl.di.FavoritesFragmentComponent
import com.katerinavp.favorites_screen_impl.viewmodel.FavoritesViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesFragment: Fragment() {
    private val binding by lazy { FragmentFavoritesBinding.inflate(layoutInflater) }
    private val viewModel: FavoritesViewModel by viewModels { viewModelFactory }

    private var favoritesFragmentComponent: FavoritesFragmentComponent? = null


    @Inject
    lateinit var viewModelFactory: FavoritesViewModel.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesFragmentComponent = (requireContext().applicationContext as FavoritesComponentProvider)
            .provideFavoritesFragmentComponent()

        favoritesFragmentComponent?.inject(this)

        binding.list.layoutManager = LinearLayoutManager(requireContext())
        binding.list.adapter = AdapterFavorites(this::deleteFavorite)


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.favoritesState.collect(::updateStateCurrency)
                }
            }
        }

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

    private fun deleteFavorite(currency: CurrencyDomainModel, position:Int ){
        (binding.list.adapter as AdapterFavorites).removeAt(position)
        viewModel.deleteFavorite(currency)
    }

    private fun updateCurrency(data: List<CurrencyDomainModel>) {
        if(data.isNotEmpty()) {
            binding.list.visibility = View.VISIBLE
            binding.progress.visibility = View.GONE
            binding.date.text = data.first().date.convertDateToString()
            binding.emptyFavorites.visibility = View.GONE
            (binding.list.adapter as AdapterFavorites).addAll(data)
        }else{
            binding.dateTxt.visibility = View.GONE
            binding.list.visibility = View.GONE
            binding.progress.visibility = View.GONE
            binding.emptyFavorites.visibility = View.VISIBLE
        }
    }
}