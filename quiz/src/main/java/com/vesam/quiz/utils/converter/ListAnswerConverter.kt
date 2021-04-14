package com.vesam.quiz.utils.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vesam.quiz.data.model.quiz_detail.Answer
import java.util.*
import kotlin.collections.ArrayList

class ListAnswerConverter {
    companion object {
        var gson = Gson()

        @TypeConverter
        @JvmStatic
        fun fromTimestamp(data: String?): ArrayList<Answer>? {

            val listType = object : TypeToken<ArrayList<String>>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun someObjectListToString(someObjects: ArrayList<Answer>?): String? {
            return gson.toJson(someObjects)
        }
    }
}