package com.katerinavp.currency_api.repository

import com.katerinavp.currency_api.model.CurrencyDomainModel

interface FavoritesRepository {
    suspend fun getFavorites(): List<CurrencyDomainModel>

    suspend fun insertFavorite(currency: CurrencyDomainModel)

    suspend fun deleteFavorite(currency: CurrencyDomainModel)
}