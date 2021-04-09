package com.vesam.quiz.data.model.quiz_detail


import com.google.gson.annotations.SerializedName
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BASE_URL_IMAGE_AND_VIDEO_VALUE

data class QuizDescription(
    @SerializedName("content")
    val content: String,
    @SerializedName("format")
    val format: String
) {
    val urlContent
        get() = BASE_URL_IMAGE_AND_VIDEO_VALUE + content
}