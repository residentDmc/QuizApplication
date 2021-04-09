package com.vesam.quiz.data.model.quiz_detail


import com.google.gson.annotations.SerializedName
import com.vesam.quiz.utils.build_config.BuildConfig

data class Description(
    @SerializedName("content")
    val content: String,
    @SerializedName("format")
    val format: String
){
    val urlContent
        get() = BuildConfig.BASE_URL_IMAGE_AND_VIDEO_VALUE + content
}