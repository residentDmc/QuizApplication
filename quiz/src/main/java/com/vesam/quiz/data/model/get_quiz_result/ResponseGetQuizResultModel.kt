package com.vesam.quiz.data.model.get_quiz_result


import com.google.gson.annotations.SerializedName
import com.vesam.quiz.data.model.get_quiz_result.Quiz

data class ResponseGetQuizResultModel(
    @SerializedName("quiz")
    val quiz: Quiz
)