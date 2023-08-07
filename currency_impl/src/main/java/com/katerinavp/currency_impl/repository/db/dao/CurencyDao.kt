package com.katerinavp.currency_impl.repository.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.katerinavp.currency_impl.repository.db.model.CurrencyDbModel

@Dao
abstract class CurrencyDao {
    @Query("SELECT * FROM ${CurrencyDbModel.TABLE_NAME}")
    abstract suspend fun getCurrency(): List<CurrencyDbModel>?

    @Query("DELETE FROM ${CurrencyDbModel.TABLE_NAME}")
    abstract fun removeAllCurrency()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCurrency(currency: List<CurrencyDbModel>)

    @Transaction
    open suspend fun updateIfExistsOrInsertCurrency(currency: List<CurrencyDbModel>) {
        removeAllCurrency()
        insertCurrency(currency)
    }
}