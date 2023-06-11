package com.katerinavp.currency.view.fragments

import com.katerinavp.currency.model.data.ModelResponseNetwork

sealed class CurrencyState {

    data class Success(val data: ModelResponseNetwork) : CurrencyState()
    data class Error(val error: Throwable) : CurrencyState()
    object Empty : CurrencyState()

}