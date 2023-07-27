package com.katerinavp.currency.model.data

import com.google.gson.annotations.SerializedName
import java.util.Date

class ModelResponseNetwork (
    @SerializedName("Date")
    val date: String,
    @SerializedName("PreviousDate")
    val previousDate: String,
    @SerializedName("PreviousURL")
    val previousURL: String,
    @SerializedName("Timestamp")
    val timestamp: Date,
    @SerializedName("Valute")
    val valute: Map<String, CurrencyNetwork>
    )