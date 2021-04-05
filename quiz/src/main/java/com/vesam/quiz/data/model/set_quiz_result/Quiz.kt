package com.vesam.quiz.data.model.set_quiz_result


import com.google.gson.annotations.SerializedName

data class Quiz(
    @SerializedName("id")
    val id: Int,
    @SerializedName("result")
    val result: Result,
    @SerializedName("title")
    val title: String
)