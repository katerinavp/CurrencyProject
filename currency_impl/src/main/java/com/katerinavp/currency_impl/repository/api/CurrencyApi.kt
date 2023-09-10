package com.katerinavp.currency_impl.repository.api

import com.katerinavp.currency_impl.repository.api.model.ResponseNetworkModel
import retrofit2.http.GET

/**
 * API
 */

interface CurrencyApi {
    @GET("daily_json.js")
    suspend fun getCurrency(): ResponseNetworkModel
}