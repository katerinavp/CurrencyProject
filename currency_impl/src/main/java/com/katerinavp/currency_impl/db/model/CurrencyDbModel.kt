package com.katerinavp.currency_impl.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.katerinavp.currency_impl.db.model.CurrencyDbModel.Companion.TABLE_NAME
import java.util.Date

@Entity(tableName = TABLE_NAME,)
data class CurrencyDbModel(
    @PrimaryKey()
    @ColumnInfo(name = COLUMN_NUM_CODE)
    val code: String,

    @ColumnInfo(name = COLUMN_NAME)
    val name: String,

    @ColumnInfo(name = COLUMN_VALUE)
    val value: Double,

    @ColumnInfo(name = COLUMN_TIME_STAMP)
    val date: Date,

    ) {

    companion object {
        const val TABLE_NAME = "currency"
        const val COLUMN_NUM_CODE = "charCode"
        const val COLUMN_NAME = "name"
        const val COLUMN_VALUE = "value"
        private const val COLUMN_TIME_STAMP = "timestamp"
    }
}