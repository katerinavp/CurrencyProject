package com.katerinavp.currency_impl.repository

import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_api.repository.FavoritesRepository
import com.katerinavp.currency_impl.db.dao.FavoritesDao
import com.katerinavp.currency_impl.extension.domainToDbModel
import com.katerinavp.currency_impl.extension.fromFavToDomainModel
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val dao: FavoritesDao,
) : FavoritesRepository {

    override suspend fun getFavorites(): List<CurrencyDomainModel> {
        return dao.getFavoritesWithId().map{favoritesWithCUrrency -> favoritesWithCUrrency.fromFavToDomainModel()}
    }

    override suspend fun insertFavorites(currency: CurrencyDomainModel) {
        dao.insertFavorites(currency.domainToDbModel())
    }
}