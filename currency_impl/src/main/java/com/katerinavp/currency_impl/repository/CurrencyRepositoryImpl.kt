package com.katerinavp.currency_impl.repository

import com.katerinavp.currency_api.model.CurrencyDomainModel
import com.katerinavp.currency_api.repository.CurrencyRepository
import com.katerinavp.currency_impl.api.CurrencyApi
import com.katerinavp.currency_impl.db.AppDatabase
import com.katerinavp.currency_impl.db.dao.CurrencyDao
import com.katerinavp.currency_impl.db.model.CurrencyDbModel
import com.katerinavp.currency_impl.extension.convertToCurrencyDbModel
import com.katerinavp.currency_impl.extension.currencyDbToDomain
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: CurrencyApi,
    private val appDatabase: AppDatabase,
//    : CurrencyDao,
) : CurrencyRepository {

    override suspend fun getCurrency(search: String, forced: Boolean): List<CurrencyDomainModel> {
        val currencyModel = appDatabase.currencyDao.getCurrency("%$search%")
        if (currencyModel?.isNotEmpty() == true && !forced) {
            return setFavoritesFlag(currencyModel).map{it.currencyDbToDomain()}
        }
        return getCurrencyServer().map{it.currencyDbToDomain()}
    }

    override suspend fun updateCurrency(forced: Boolean) {
        val currencyNetwork = api.getCurrency()
        appDatabase.currencyDao.updateCurrency(setFavoritesFlag(currencyNetwork.convertToCurrencyDbModel()))
    }

    private suspend fun getCurrencyServer(): List<CurrencyDbModel> {
        val currencyNetwork = api.getCurrency()
        val currencyDbModel = currencyNetwork.convertToCurrencyDbModel()
        saveCurrencyToDatabase(currencyDbModel)
        return currencyDbModel
    }

    private suspend fun setFavoritesFlag(currencyDbModel: List<CurrencyDbModel>): List<CurrencyDbModel>{
        val favoritesDbModel = appDatabase.favoritesDao.getFavoritesWithId()
        if(favoritesDbModel.isNotEmpty()){
           for(i in currencyDbModel.indices){
               for(favorite in favoritesDbModel ){
                   if(currencyDbModel[i].code == favorite.favorites.code){
                       currencyDbModel[i].isFavorites = 1
                   }else{
                       currencyDbModel[i].isFavorites = 0
                   }
               }
           }

        }
        return currencyDbModel
    }


    private suspend fun saveCurrencyToDatabase(currencyDbModel: List<CurrencyDbModel>) {
        appDatabase.currencyDao.updateIfExistsOrInsertCurrency(currencyDbModel)
    }
}