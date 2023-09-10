package com.katerinavp.currencies_screen_impl.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.katerinavp.core.ResponseState
import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_api.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(private val repo: CurrencyRepository) : ViewModel() {

    private val _currencyState = MutableStateFlow<ResponseState<List<CurrencyDomainModel>>>(ResponseState.Empty)
    val currencyState: StateFlow<ResponseState<List<CurrencyDomainModel>>> = _currencyState

    init {
        getCurrency("")
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

    class Factory @Inject constructor(
        private val repo: CurrencyRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
                return CurrencyViewModel(repo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

