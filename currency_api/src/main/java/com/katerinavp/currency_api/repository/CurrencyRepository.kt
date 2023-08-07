package com.katerinavp.currency_api.repository

import com.katerinavp.currency_api.model.CurrencyDomainModel

interface CurrencyRepository {
    suspend fun getCurrency(): List<CurrencyDomainModel>
}