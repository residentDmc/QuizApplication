package com.vesam.quiz.data.model.quiz_detail


import com.google.gson.annotations.SerializedName

data class ResponseQuizDetailModel(
    @SerializedName("details")
    val details: Details,
    @SerializedName("questions")
    val questions: List<Question>
)