package com.vesam.quiz.data.model.error_model


import com.google.gson.annotations.SerializedName

data class ResponseErrorModel(
    @SerializedName("message")
    val message: String
)