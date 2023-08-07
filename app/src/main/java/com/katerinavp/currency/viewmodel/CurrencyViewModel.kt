package com.katerinavp.currency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katerinavp.currency.view.fragments.CurrencyState
import com.katerinavp.currency_api.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(private val repo: CurrencyRepository) : ViewModel() {

    private val _currencyState = MutableStateFlow<CurrencyState>(CurrencyState.Empty)
    val currencyState: StateFlow<CurrencyState> = _currencyState

    init {
        getCurrency()
    }

    private fun getCurrency() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currencyResult = repo.getCurrency()
                _currencyState.emit(CurrencyState.Success(currencyResult))
            } catch (e: Throwable) {
                _currencyState.emit(CurrencyState.Error(e))
            }
        }
    }
}

