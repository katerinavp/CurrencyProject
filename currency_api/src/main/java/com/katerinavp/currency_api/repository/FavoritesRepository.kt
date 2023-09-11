package com.katerinavp.currency_api.repository

import com.katerinavp.currency_api.model.CurrencyDomainModel

interface FavoritesRepository {

    suspend fun getFavorites( ): List<CurrencyDomainModel>

    suspend fun insertFavorites(currency: CurrencyDomainModel)
}