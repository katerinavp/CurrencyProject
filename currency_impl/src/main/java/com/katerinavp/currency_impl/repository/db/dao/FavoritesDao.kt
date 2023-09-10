package com.katerinavp.currency_impl.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.katerinavp.currency_impl.repository.db.model.CurrencyDbModel
import com.katerinavp.currency_impl.repository.db.model.FavoritesDbModel

@Dao
abstract class FavoritesDao {
    @Query("SELECT * FROM ${FavoritesDbModel.TABLE_NAME}")
    abstract suspend fun getFavorites(): List<CurrencyDbModel>

//    @Query("DELETE FROM ${FavoritesDbModel.TABLE_NAME}")
//    abstract fun removeAllFavorites()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCurrency(currency: List<CurrencyDbModel>)

//    @Transaction
//    open suspend fun updateIfExistsOrInsertFavorites(currency: List<CurrencyDbModel>) {
//        removeAllFavorites()
//        insertCurrency(currency)
//    }
}