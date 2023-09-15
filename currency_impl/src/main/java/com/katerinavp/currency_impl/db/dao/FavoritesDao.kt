package com.katerinavp.currency_impl.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.katerinavp.currency_impl.db.model.FavoritesDbModel
import com.katerinavp.currency_impl.db.model.FavoritesWithCurrency

@Dao
abstract class FavoritesDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertFavorites(currency: FavoritesDbModel)

    @Query("SELECT * FROM currency, favorites  where  currency.charCode = favorites.charCodeFavorites ")
    abstract suspend fun getFavoritesWithId(): List<FavoritesWithCurrency>
}