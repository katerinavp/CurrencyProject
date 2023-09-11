package com.katerinavp.currency_impl.repository

import com.katerinavp.currency_impl.repository.api.CurrencyApi
import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_api.repository.CurrencyRepository
import com.katerinavp.currency_api.repository.FavoritesRepository
import com.katerinavp.currency_impl.repository.db.dao.CurrencyDao
import com.katerinavp.currency_impl.repository.db.dao.FavoritesDao
import com.katerinavp.currency_impl.repository.db.model.CurrencyDbModel
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val api: CurrencyApi,
    private val dao: FavoritesDao,
) : FavoritesRepository {

    override suspend fun getFavorites(): List<CurrencyDomainModel> {
       return dao.getFavorites().map(::dbToDomainModel)
    }

    override suspend fun insertFavorites(currency: CurrencyDomainModel) {
        dao.insertCurrency(currency as CurrencyDbModel)
    }

    private fun dbToDomainModel(dbModel: CurrencyDbModel): CurrencyDomainModel {
        return CurrencyDomainModel(
            id = dbModel.id,
            code = dbModel.code,
            name = dbModel.name,
            value = dbModel.value,
            date = dbModel.date,
        )
    }
}