package com.katerinavp.currency_api.model

import java.util.Date


data class CurrencyDomainModel(
    val id: Long = 0,
    val code: String,
    val name: String,
    val value: Double,
    val date: Date,
    var isFavorites: Int? = 0,
)