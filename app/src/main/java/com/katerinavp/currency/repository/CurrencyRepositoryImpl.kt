package com.katerinavp.currency.repository

import com.katerinavp.currency.common.extension.convertToCurrencyDbModel
import com.katerinavp.currency.data.api.CurrencyApi
import com.katerinavp.currency.data.db.AppDatabase
import com.katerinavp.currency.data.db.model.CurrencyDbModel
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val api: CurrencyApi,
                                                 private val db: AppDatabase
) :
    CurrencyRepository {

    override suspend fun getCurrency(): List<CurrencyDbModel>{

        val currencyModel = db.currencyDao.getCurrency()
        if (currencyModel?.isNotEmpty() == true){
            return currencyModel
        }
        return  getCurrencyServer()


    }

    private suspend fun getCurrencyServer(): List<CurrencyDbModel> {
        val currencyNetwork = api.getCurrency()
        val currencyDbModel = currencyNetwork.convertToCurrencyDbModel()
        saveCurrencyToDatabase(currencyDbModel)
        return currencyDbModel
    }

    private suspend fun saveCurrencyToDatabase(currencyDbModel: List<CurrencyDbModel>) {
        db.currencyDao.updateIfExistsOrInsertCurrency(currencyDbModel)
    }
}