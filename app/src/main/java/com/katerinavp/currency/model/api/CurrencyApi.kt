package com.katerinavp.currency.model.api

import com.katerinavp.currency.model.data.ModelResponseNetwork
import retrofit2.http.GET

/**
 * API
 */

interface CurrencyApi {

    @GET("daily_json.js")
    suspend fun getCurrency(): ModelResponseNetwork
}