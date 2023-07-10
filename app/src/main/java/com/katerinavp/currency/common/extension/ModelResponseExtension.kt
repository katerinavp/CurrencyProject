package com.katerinavp.currency.common.extension

import com.katerinavp.currency.model.data.ConvertCurrency
import com.katerinavp.currency.model.data.ModelResponseNetwork

fun ModelResponseNetwork.convertTo(): List<ConvertCurrency> {
    return this.valute.values.map { currencyModel ->
        ConvertCurrency(
            codeCurrency = currencyModel.charCode,
            nameCurrency = currencyModel.name,
            valueCurrency = currencyModel.value,
        )
    }
}

    fun ModelResponseNetwork.returnCharCode(): List<String> {
        return this.valute.values.map { currencyModel ->
            currencyModel.charCode

        }


}
