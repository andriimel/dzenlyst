package com.am.dzenlyst.data.local.task

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    companion object {
        private val gson = Gson()
    }
    @TypeConverter
    fun fromSubtasks(list: List<String>): String = gson.toJson(list)

    @TypeConverter
    fun toSubtasks(data: String): List<String> =
        gson.fromJson(data, object : TypeToken<List<String>>() {}.type)
}