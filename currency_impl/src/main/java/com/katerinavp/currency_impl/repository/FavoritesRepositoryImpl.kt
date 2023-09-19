package com.katerinavp.currency_impl.repository

import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_api.repository.FavoritesRepository
import com.katerinavp.currency_impl.db.AppDatabase
import com.katerinavp.currency_impl.extension.domainToDbModel
import com.katerinavp.currency_impl.extension.fromFavToDomainModel
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : FavoritesRepository {

    override suspend fun getFavorites(): List<CurrencyDomainModel> {
        return appDatabase.favoritesDao.getFavoritesWithId().map{favoritesWithCurrency -> favoritesWithCurrency.fromFavToDomainModel()}
    }

    override suspend fun insertFavorite(currency: CurrencyDomainModel) {
        appDatabase.favoritesDao.insertFavorite(currency.domainToDbModel())
        appDatabase.currencyDao.updateFavoritesStatus(1, currency.domainToDbModel().code)
    }

    override suspend fun deleteFavorite(currency: CurrencyDomainModel) {
        appDatabase.favoritesDao.deleteFavorite(currency.domainToDbModel().code)
        appDatabase.currencyDao.updateFavoritesStatus(0, currency.domainToDbModel().code)
    }
}