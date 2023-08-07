package com.katerinavp.currency.view.fragments

import com.katerinavp.currency_api.model.CurrencyDomainModel


sealed class CurrencyState {

    data class Success(val data: List<CurrencyDomainModel>) : CurrencyState()
    data class Error(val error: Throwable) : CurrencyState()
    data class SumResult(val data: String) : CurrencyState()
    object Empty : CurrencyState()

}