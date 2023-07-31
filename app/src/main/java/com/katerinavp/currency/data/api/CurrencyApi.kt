package com.katerinavp.currency.data.api

import com.katerinavp.currency.data.api.model.ResponseNetworkModel
import retrofit2.http.GET

/**
 * API
 */

interface CurrencyApi {

    @GET("daily_json.js")
    suspend fun getCurrency(): ResponseNetworkModel
}