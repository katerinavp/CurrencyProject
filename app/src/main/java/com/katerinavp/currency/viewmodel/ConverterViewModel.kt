package com.katerinavp.currency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katerinavp.currency.view.fragments.CurrencyState
import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_api.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConverterViewModel @Inject constructor(private val repo: CurrencyRepository) : ViewModel() {

    private val _converterState = MutableStateFlow<CurrencyState>(CurrencyState.Empty)
    val converterState: StateFlow<CurrencyState> = _converterState


    init {
        getCurrency()
    }

    private fun getCurrency() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currencyResult = repo.getCurrency()
                _converterState.emit(CurrencyState.Success(currencyResult))
            } catch (e: Throwable) {
                _converterState.emit(CurrencyState.Error(e))
            }
        }

    }

    fun convert(sum: Int, data: CurrencyDomainModel) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _converterState.value =
                    CurrencyState.SumResult(Math.round(data.value * sum).toString())

            } catch (e: Throwable) {
                _converterState.emit(CurrencyState.Error(e))
            }
        }
    }


}

