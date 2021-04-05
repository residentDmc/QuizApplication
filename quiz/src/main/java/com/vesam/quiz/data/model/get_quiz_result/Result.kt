package com.vesam.quiz.data.model.get_quiz_result


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("correct_answers_count")
    val correctAnswersCount: Int,
    @SerializedName("incorrect_answers_count")
    val incorrectAnswersCount: Int,
    @SerializedName("passed")
    val passed: Int,
    @SerializedName("period_time")
    val periodTime: Int,
    @SerializedName("questions_count")
    val questionsCount: Int
)