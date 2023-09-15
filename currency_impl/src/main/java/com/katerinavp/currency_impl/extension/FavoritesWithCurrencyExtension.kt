package com.katerinavp.currency_impl.extension

import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_impl.db.model.FavoritesWithCurrency

fun FavoritesWithCurrency.fromFavToDomainModel(): CurrencyDomainModel {
    return CurrencyDomainModel(
        code = this.currency.code,
        name = this.currency.name,
        value = this.currency.value,
        date = this.currency.date,
    )
}