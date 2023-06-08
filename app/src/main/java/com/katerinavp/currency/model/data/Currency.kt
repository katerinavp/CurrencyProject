package com.katerinavp.currency.model.data

import com.google.gson.annotations.SerializedName

class Currency (
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