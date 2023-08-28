package com.katerinavp.converter_screen_impl.fragments

import com.katerinavp.currency_api.model.CurrencyDomainModel

data class UiState(
    val input: String = "",
    val result: String = "", // cумма
    val currencies: List<CurrencyDomainModel> = emptyList(),
    val selectedCurrency: Int? = null,
)
