package com.katerinavp.currency_impl.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.katerinavp.currency_impl.db.model.CurrencyDbModel
import com.katerinavp.currency_impl.db.model.FavoritesDbModel
import com.katerinavp.currency_impl.db.model.FavoritesWithCurrency

@Dao
abstract class FavoritesDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertFavorite(currency: FavoritesDbModel)

    @Query("DELETE FROM ${FavoritesDbModel.TABLE_NAME} WHERE charCodeFavorites = :code")
    abstract fun deleteFavorite(code: String)

    @Query("SELECT * FROM currency, favorites  where  currency.charCode = favorites.charCodeFavorites ")
    abstract suspend fun getFavoritesWithId(): List<FavoritesWithCurrency>
}