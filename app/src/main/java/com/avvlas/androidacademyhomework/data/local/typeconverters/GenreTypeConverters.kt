package com.avvlas.androidacademyhomework.data.local.typeconverters

import androidx.room.TypeConverter
import com.avvlas.androidacademyhomework.model.Genre
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.ParameterizedType

class GenreTypeConverters {
    private val moshi = Moshi.Builder().build()
    private val list : ParameterizedType = Types.newParameterizedType(List::class.java, Genre::class.java)
    private val jsonAdapter: JsonAdapter<List<Genre>> = moshi.adapter(list)

    @TypeConverter
    fun listToJsonStr(list: List<Genre>?): String? {
        return jsonAdapter.toJson(list)
    }

    @TypeConverter
    fun jsonStrToList(jsonStr: String?): List<Genre>? {
        return jsonStr?.let { jsonAdapter.fromJson(jsonStr) }
    }
}