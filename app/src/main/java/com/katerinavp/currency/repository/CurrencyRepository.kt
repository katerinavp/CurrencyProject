package com.katerinavp.currency.repository

import kotlinx.coroutines.flow.*
import com.katerinavp.currency.model.api.CurrencyApi
import com.katerinavp.currency.model.data.Currency
import kotlinx.coroutines.flow.channelFlow

class CurrencyRepository(private val api : CurrencyApi) {

    fun getCurrency(): Flow<Currency>{
        return channelFlow { api.getCurrency() }
    }
}