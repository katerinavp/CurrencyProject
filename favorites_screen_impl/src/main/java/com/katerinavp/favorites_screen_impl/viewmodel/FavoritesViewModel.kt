package com.katerinavp.favorites_screen_impl.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.katerinavp.core.ResponseState
import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_api.repository.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel@Inject constructor(private val repo: FavoritesRepository) : ViewModel() {

    private val _favoritesState = MutableStateFlow<ResponseState<List<CurrencyDomainModel>>>(
        ResponseState.Empty)
    val favoritesState: StateFlow<ResponseState<List<CurrencyDomainModel>>> = _favoritesState

    init {
        getCurrency()
    }

    private fun getCurrency() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currencyResult = repo.getFavorites()
                _favoritesState.emit(ResponseState.Success(currencyResult))
            } catch (e: Throwable) {
                _favoritesState.emit(ResponseState.Error(e))
            }
        }
    }

    class Factory @Inject constructor(
        private val repo: FavoritesRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
                return FavoritesViewModel(repo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}