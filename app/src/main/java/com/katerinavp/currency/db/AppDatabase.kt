package com.katerinavp.currency_api.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.katerinavp.currency_impl.repository.db.converters.DateConverters
import com.katerinavp.currency_impl.repository.db.dao.CurrencyDao
import com.katerinavp.currency_impl.repository.db.model.CurrencyDbModel

/**
 * База данных
 *
 */
@Database(
    version = 1, exportSchema = true, entities = [CurrencyDbModel::class],
    autoMigrations = []
)

@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val currencyDao: CurrencyDao
}