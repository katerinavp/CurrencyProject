package com.katerinavp.currency.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImp {

    fun getApi(): CurrencyApi {

        val converter = GsonConverterFactory.create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converter)
            .build()

        return retrofit.create(CurrencyApi::class.java)
    }

    companion object {
        const val BASE_URL = "https://www.cbr-xml-daily.ru/"
    }


}

