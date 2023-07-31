package com.katerinavp.currency.view.fragments

import com.katerinavp.currency.data.db.model.CurrencyDbModel


sealed class CurrencyState {

    data class Success(val data: List<CurrencyDbModel>) : CurrencyState()
    data class Error(val error: Throwable) : CurrencyState()
    data class SumResult(val data: String): CurrencyState()
    object Empty : CurrencyState()

}