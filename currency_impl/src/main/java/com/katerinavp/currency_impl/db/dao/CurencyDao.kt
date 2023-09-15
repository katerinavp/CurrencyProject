package com.katerinavp.currency_impl.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.katerinavp.currency_impl.db.model.CurrencyDbModel

@Dao
abstract class CurrencyDao {
    @Query("SELECT * FROM ${CurrencyDbModel.TABLE_NAME} WHERE name LIKE :search OR charCode LIKE :search  ")
    abstract suspend fun getCurrency(search: String): List<CurrencyDbModel>?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun updateCurrency(currency: List<CurrencyDbModel>)

    @Query("DELETE FROM ${CurrencyDbModel.TABLE_NAME}")
    abstract suspend fun removeAllCurrency()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCurrency(currency: List<CurrencyDbModel>)

    @Transaction
    open suspend fun updateIfExistsOrInsertCurrency(currency: List<CurrencyDbModel>) {
        removeAllCurrency()
        insertCurrency(currency)
    }
}