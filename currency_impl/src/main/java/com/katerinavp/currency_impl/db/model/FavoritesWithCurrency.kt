package com.katerinavp.currency_impl.db.model

import androidx.room.Embedded

data class FavoritesWithCurrency(
    @Embedded  val favorites: FavoritesDbModel,
    @Embedded val currency: CurrencyDbModel
)