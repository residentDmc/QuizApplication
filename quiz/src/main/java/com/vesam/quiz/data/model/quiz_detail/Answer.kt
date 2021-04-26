package com.vesam.quiz.data.model.quiz_detail


import com.google.gson.annotations.SerializedName
import com.vesam.quiz.utils.build_config.BuildConfig
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_VIDEO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MIM_TYPE_AUDIO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MIM_TYPE_VIDEO

data class Answer(
    @SerializedName("description")
    val description: Description,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_correct")
    val isCorrect: Int,
    @SerializedName("sort")
    val sort: Int,
    @SerializedName("title")
    val title: String
) {
    var isSuccess = 0
    var isCheckLevel = 0
    var isSelected = false
    var isEnable = false
    var isCorrectItem = false
    var uriPath = ""
        get() {
            return when (description.format) {
                FORMAT_VIDEO -> title + MIM_TYPE_VIDEO
                else -> title + MIM_TYPE_AUDIO
            }
        }
}