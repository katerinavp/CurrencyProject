package com.katerinavp.currency_impl.extension

import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_impl.db.model.CurrencyDbModel

fun CurrencyDbModel.currencyDbToDomain(): CurrencyDomainModel {
    return CurrencyDomainModel(
        code = this.code,
        name = this.name,
        value = this.value,
        date = this.date,
        isFavorites = this.isFavorites,
    )
}