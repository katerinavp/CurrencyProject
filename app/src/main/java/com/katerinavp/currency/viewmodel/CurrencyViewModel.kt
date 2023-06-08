package com.katerinavp.currency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.katerinavp.currency.repository.CurrencyRepository

class CurrencyViewModel(val repo : CurrencyRepository) : ViewModel() {




}

class CurrencyViewModelFactory  constructor(
    private val repo : CurrencyRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyViewModel::class.java))
            return CurrencyViewModel(repo) as T
        else throw IllegalArgumentException()
    }
}