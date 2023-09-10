package com.katerinavp.currency_impl.repository

import com.katerinavp.currency_impl.repository.api.model.CurrencyNetworkModel
import com.katerinavp.currency_impl.repository.api.model.ResponseNetworkModel
import com.katerinavp.currency_impl.repository.db.model.CurrencyDbModel
import java.util.Date

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


