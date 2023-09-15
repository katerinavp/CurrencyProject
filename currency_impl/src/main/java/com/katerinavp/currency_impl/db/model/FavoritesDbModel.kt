package com.katerinavp.currency_impl.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.katerinavp.currency_impl.db.model.FavoritesDbModel.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME,
        foreignKeys = [ForeignKey(
            entity = CurrencyDbModel::class,
            parentColumns = arrayOf("charCode"),
            childColumns = arrayOf("charCodeFavorites"),
            onUpdate = ForeignKey.CASCADE
        )]
)
data class FavoritesDbModel(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = COLUMN_NUM_CODE_FAVORITES)
    var code: String,

    ) {

    companion object {
        const val TABLE_NAME = "favorites"
        const val COLUMN_NUM_CODE_FAVORITES = "charCodeFavorites"
    }
}