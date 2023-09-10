package com.katerinavp.currency_impl.repository

import com.katerinavp.currency_impl.repository.api.CurrencyApi
import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_api.repository.CurrencyRepository
import com.katerinavp.currency_impl.repository.db.dao.CurrencyDao
import com.katerinavp.currency_impl.repository.db.model.CurrencyDbModel
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: CurrencyApi,
    private val dao: CurrencyDao,
) : CurrencyRepository {

    override suspend fun getCurrency(search: String): List<CurrencyDomainModel> {
        val currencyModel = dao.getCurrency("%$search%")
        if (currencyModel?.isNotEmpty() == true) {
            return currencyModel.map(::dbToDomainModel)
        }
        return getCurrencyServer().map(::dbToDomainModel)
    }

    private suspend fun getCurrencyServer(): List<CurrencyDbModel> {
        val currencyNetwork = api.getCurrency()
        val currencyDbModel = currencyNetwork.convertToCurrencyDbModel()
        saveCurrencyToDatabase(currencyDbModel)
        return currencyDbModel
    }


    private suspend fun saveCurrencyToDatabase(currencyDbModel: List<CurrencyDbModel>) {
        dao.updateIfExistsOrInsertCurrency(currencyDbModel)
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