package com.katerinavp.currency.repository

import com.katerinavp.currency.data.db.model.CurrencyDbModel

interface CurrencyRepository {

suspend fun getCurrency(): List<CurrencyDbModel>
}