package com.vesam.quiz.data.model.quiz_detail


import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("content")
    val content: String,
    @SerializedName("format")
    val format: String
)