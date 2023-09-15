package com.katerinavp.currency_impl.extension

import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_impl.db.model.FavoritesDbModel

fun CurrencyDomainModel.domainToDbModel(): FavoritesDbModel {
    return FavoritesDbModel(
        code = this.code,
    )
}
