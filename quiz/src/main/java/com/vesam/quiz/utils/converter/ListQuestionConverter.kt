package com.vesam.quiz.utils.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vesam.quiz.data.model.quiz_detail.Answer
import com.vesam.quiz.data.model.quiz_detail.Question
import java.util.*

class ListQuestionConverter {
    companion object {
        var gson = Gson()

        @TypeConverter
        @JvmStatic
        fun fromTimestamp(data: String?): ArrayList<Question>? {
            val listType = object : TypeToken<ArrayList<Question>>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun someObjectListToString(someObjects: ArrayList<Question>?): String? =
            gson.toJson(someObjects)
    }
}