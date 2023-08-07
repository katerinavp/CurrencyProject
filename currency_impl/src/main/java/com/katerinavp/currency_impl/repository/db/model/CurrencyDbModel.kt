package com.katerinavp.currency_impl.repository.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.katerinavp.currency_impl.repository.db.model.CurrencyDbModel.Companion.TABLE_NAME
import java.util.Date

@Entity(tableName = TABLE_NAME)
data class CurrencyDbModel(

    @ColumnInfo(name = COLUMN_NUM_CODE)
    var code: String,

    @ColumnInfo(name = COLUMN_NAME)
    var name: String,

    @ColumnInfo(name = COLUMN_VALUE)
    var value: Double,

    @ColumnInfo(name = COLUMN_TIME_STAMP)
    var date: Date,


    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    var id: Long = 0

    companion object {
        const val TABLE_NAME = "currency"
        const val COLUMN_ID = "id"
        const val COLUMN_NUM_CODE = "numCode"
        const val COLUMN_NAME = "name"
        const val COLUMN_VALUE = "value"
        private const val COLUMN_TIME_STAMP = "timestamp"
    }
}