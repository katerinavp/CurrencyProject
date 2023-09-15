package com.katerinavp.currency_impl.api.model

import com.google.gson.annotations.SerializedName
import java.util.Date

class ResponseNetworkModel (
    @SerializedName("Date")
    val date: String,
    @SerializedName("PreviousDate")
    val previousDate: String,
    @SerializedName("PreviousURL")
    val previousURL: String,
    @SerializedName("Timestamp")
    val timestamp: Date,
    @SerializedName("Valute")
    val valute: Map<String, CurrencyNetworkModel>
    )