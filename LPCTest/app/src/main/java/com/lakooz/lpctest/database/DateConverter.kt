package com.lakooz.lpctest.database

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun fromTimestamp(mills: Long?): Date? {
       // TODO
        return if (mills == null) null else Date(mills)

    }

    @TypeConverter
    fun fromDate(date: Date?): Long?  {
        // TODO
        return  date?.time ?: 0
    }
}