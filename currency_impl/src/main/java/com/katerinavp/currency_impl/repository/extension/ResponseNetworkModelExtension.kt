package com.katerinavp.currency_impl.repository.extension

import com.katerinavp.currency_impl.repository.api.model.ConvertCurrency
import com.katerinavp.currency_impl.repository.api.model.CurrencyNetworkModel
import com.katerinavp.currency_impl.repository.api.model.ResponseNetworkModel
import com.katerinavp.currency_impl.repository.db.model.CurrencyDbModel
import java.util.Date

fun ResponseNetworkModel.convertTo(): List<ConvertCurrency> {
    return this.valute.values.map { currencyModel ->
        ConvertCurrency(
            codeCurrency = currencyModel.charCode,
            nameCurrency = currencyModel.name,
            valueCurrency = currencyModel.value,
        )
    }
}

fun ResponseNetworkModel.returnCharCode(): List<String> {
    return this.valute.values.map { currencyModel ->
        currencyModel.charCode

    }
}

fun from(currencyNetworkModel: CurrencyNetworkModel, date: Date): CurrencyDbModel {
    return CurrencyDbModel(
        code = currencyNetworkModel.charCode,
        name = currencyNetworkModel.name,
        value = currencyNetworkModel.value,
        date = date

    )
}

fun ResponseNetworkModel.convertToCurrencyDbModel(): List<CurrencyDbModel> {
    return this.valute.values.map {
        from(it, this.timestamp)
    }
}



