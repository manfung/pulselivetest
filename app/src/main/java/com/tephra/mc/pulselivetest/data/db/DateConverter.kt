package com.tephra.mc.latestnews.data.db

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun toDate(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun toLong(value: Date?): Long? {
        return (if (value == null) null else value!!.time)!!.toLong()
    }
}

