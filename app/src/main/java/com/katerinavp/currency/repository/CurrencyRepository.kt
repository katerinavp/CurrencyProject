package com.katerinavp.currency.repository

import com.katerinavp.currency.model.api.CurrencyApi
import com.katerinavp.currency.model.data.ModelResponseNetwork
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val api: CurrencyApi) :
    CurrencyRepository {

    override suspend fun getCurrency(): ModelResponseNetwork {
        return api.getCurrency()
    }
}

interface CurrencyRepository {
    suspend fun getCurrency(): ModelResponseNetwork
}