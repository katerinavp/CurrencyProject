package com.katerinavp.currency_impl.repository

import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_api.repository.CurrencyRepository
import com.katerinavp.currency_impl.api.CurrencyApi
import com.katerinavp.currency_impl.db.dao.CurrencyDao
import com.katerinavp.currency_impl.db.model.CurrencyDbModel
import com.katerinavp.currency_impl.extension.convertToCurrencyDbModel
import com.katerinavp.currency_impl.extension.currencyDbToDomain
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: CurrencyApi,
    private val dao: CurrencyDao,
) : CurrencyRepository {

    override suspend fun getCurrency(search: String, forced: Boolean): List<CurrencyDomainModel> {
        val currencyModel = dao.getCurrency("%$search%")
        if (currencyModel?.isNotEmpty() == true && !forced) {
            return currencyModel.map{it.currencyDbToDomain()}
        }
        return getCurrencyServer().map{it.currencyDbToDomain()}
    }

    override suspend fun updateCurrency(forced: Boolean) {
        val currencyNetwork = api.getCurrency()
        dao.updateCurrency(currencyNetwork.convertToCurrencyDbModel())
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
}