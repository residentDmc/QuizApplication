package com.vesam.quiz.data.model.set_quiz_result


import com.google.gson.annotations.SerializedName
import com.vesam.quiz.data.model.set_quiz_result.Quiz

data class ResponseSetQuizResultModel(
    @SerializedName("quiz")
    val quiz: Quiz
)