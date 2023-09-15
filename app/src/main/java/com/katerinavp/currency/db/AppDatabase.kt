package com.katerinavp.currency.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.katerinavp.currency_impl.db.converters.DateConverters
import com.katerinavp.currency_impl.db.dao.CurrencyDao
import com.katerinavp.currency_impl.db.dao.FavoritesDao
import com.katerinavp.currency_impl.db.model.CurrencyDbModel
import com.katerinavp.currency_impl.db.model.FavoritesDbModel

/**
 * База данных
 *
 */
@Database(
    version = 1, exportSchema = true, entities = [CurrencyDbModel::class, FavoritesDbModel::class],
    autoMigrations = [

    ]
)

@TypeConverters(DateConverters::class)

abstract class AppDatabase : RoomDatabase() {
    abstract val currencyDao: CurrencyDao
    abstract val favoritesDao: FavoritesDao
}