package com.katerinavp.currencies_screen_impl.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.katerinavp.core.ResponseState
import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_api.repository.CurrencyRepository
import com.katerinavp.currency_api.repository.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(
    private val repo: CurrencyRepository,
    private val repoFavorites: FavoritesRepository
) : ViewModel() {

    private val _currencyState =
        MutableStateFlow<ResponseState<List<CurrencyDomainModel>>>(ResponseState.Empty)
    val currencyState: StateFlow<ResponseState<List<CurrencyDomainModel>>> = _currencyState

    private val _updateCurrencyState = MutableStateFlow<ResponseState<Boolean>>(ResponseState.Empty)
    val updateCurrencyState: StateFlow<ResponseState<Boolean>> = _updateCurrencyState

    private val _saveFavoritesState = MutableStateFlow<ResponseState<Boolean>>(ResponseState.Empty)
    val saveFavoritesState: StateFlow<ResponseState<Boolean>> = _saveFavoritesState

    init {
        getCurrency("")
    }

    fun updateCurrency(forced: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.updateCurrency(forced)
                _updateCurrencyState.emit(ResponseState.Success(true))
            } catch (e: Throwable) {
                _updateCurrencyState.emit(ResponseState.Error(e))
            }
        }
    }

    fun getCurrency(search: String, forced: Boolean = false) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currencyResult = repo.getCurrency(search, forced)
                _currencyState.emit(ResponseState.Success(currencyResult))
            } catch (e: Throwable) {
                _currencyState.emit(ResponseState.Error(e))
            }
        }
    }


    fun saveFavorites(currency: CurrencyDomainModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repoFavorites.insertFavorite(currency)
                _saveFavoritesState.emit(ResponseState.Success(true))
            } catch (e: Throwable) {
                _saveFavoritesState.emit(ResponseState.Error(e))
            }
        }
    }

    fun deleteFavorites(currency: CurrencyDomainModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repoFavorites.deleteFavorite(currency)
                _saveFavoritesState.emit(ResponseState.Success(true))
            } catch (e: Throwable) {
                _saveFavoritesState.emit(ResponseState.Error(e))
            }
        }
    }


    class Factory @Inject constructor(
        private val repo: CurrencyRepository,
        private val repoFavorites: FavoritesRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
                return CurrencyViewModel(repo, repoFavorites) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

