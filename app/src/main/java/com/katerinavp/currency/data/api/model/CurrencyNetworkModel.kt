package com.katerinavp.currency.data.api.model

import com.google.gson.annotations.SerializedName

class CurrencyNetworkModel (
    @SerializedName("ID")
    val id: String,

    @SerializedName("NumCode")
    val numCode: Int,

    @SerializedName("CharCode")
    val charCode: String,

    @SerializedName("Nominal")
    val nominal: Int,

    @SerializedName("Name")
    val name: String,

    @SerializedName("Value")
    val value: Double,

    @SerializedName("Previous")
    val previous: Double
)