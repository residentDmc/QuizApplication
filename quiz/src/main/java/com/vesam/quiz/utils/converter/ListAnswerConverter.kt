package com.vesam.quiz.utils.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vesam.quiz.data.model.quiz_detail.Answer
import java.util.*

class ListAnswerConverter {
    companion object {
        var gson = Gson()

        @TypeConverter
        @JvmStatic
        fun fromTimestamp(data: String?): List<Answer>?
        {
            if (data == null)
            {
                return Collections.emptyList()
            }

            val listType = object : TypeToken<List<String>>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun someObjectListToString(someObjects: List<Answer>?): String?
        {
            return gson.toJson(someObjects)
        }
    }
}