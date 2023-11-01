package com.example.customappproject

import androidx.room.TypeConverter
import java.time.LocalDateTime

class DatabaseTypeConverters {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        //return value?.let { LocalDateTime(it) }
        return LocalDateTime.parse(value)
    }

    @TypeConverter
    fun localDateTimeToTimestamp(date: LocalDateTime?): String? {
        return date.toString()
    }

    //LocalDateTime.parse("22/10/23 19:00", DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"))

}