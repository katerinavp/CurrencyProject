package com.katerinavp.converter_screen_impl.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.katerinavp.converter_screen_impl.fragments.UiState
import com.katerinavp.core.ResponseState
import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_api.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

class ConverterViewModel @Inject constructor(private val repo: CurrencyRepository) : ViewModel() {

    private val _converterState = MutableStateFlow<ResponseState<UiState>>(ResponseState.Empty)
    val converterState: StateFlow<ResponseState<UiState>> = _converterState


    init {
        getCurrency(search = "")
    }

    private fun getCurrency(search: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currencyResult = repo.getCurrency(search)
                _converterState.value = ResponseState.Success(
                    UiState(
                        currencies = currencyResult,

                    )
                )
            } catch (e: Throwable) {
                _converterState.emit(ResponseState.Error(e))
            }
        }

    }

    fun convert(sum: Int, data: CurrencyDomainModel, index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currentState = (_converterState.value as? ResponseState.Success<UiState>)
                    ?.data
                    ?: return@launch
                _converterState.value = ResponseState.Success(
                    currentState.copy(
                        result = (data.value * sum).roundToInt().toString(),
                        selectedCurrency = index,
                    )
                )

//                _converterState.value = ResponseState.Success(
//                    UiState(
//                        result = (data.value * sum).roundToInt().toString(),
//                        currencies = currentState,
//                        selectedCurrency = data.code,
//                    )
//                )

//                _converterState.update {
//                    ResponseState.Success(
//                        UiState(
//                            currencies = currentState.,
//                            result = (data.value * sum).roundToInt().toString(),
//                            selectedCurrency =
//                        ))
//                }


            } catch (e: Throwable) {
                _converterState.emit(ResponseState.Error(e))
            }
        }
    }

    class Factory @Inject constructor(
        private val repo: CurrencyRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ConverterViewModel::class.java)) {
                return ConverterViewModel(repo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

