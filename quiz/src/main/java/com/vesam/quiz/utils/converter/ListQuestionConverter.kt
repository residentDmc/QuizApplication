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
        fun fromTimestamp(data: String?): List<Question>?
        {
            if (data == null)
                return Collections.emptyList()
            val listType = object : TypeToken<List<Question>>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun someObjectListToString(someObjects: List<Question>?): String? = gson.toJson(someObjects)
    }
}