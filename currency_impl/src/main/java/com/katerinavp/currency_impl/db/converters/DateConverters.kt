package com.katerinavp.currency_impl.db.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverters {

     @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}