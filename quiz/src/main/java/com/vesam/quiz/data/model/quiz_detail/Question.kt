package com.vesam.quiz.data.model.quiz_detail


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import com.vesam.quiz.utils.build_config.BuildConfig
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_VIDEO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MIM_TYPE_AUDIO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MIM_TYPE_VIDEO


data class Question(
    @SerializedName("answers")
    @ColumnInfo(name = "answers")
    val answers: ArrayList<Answer>,
    @SerializedName("description")
    @ColumnInfo(name = "description")
    val quizDescription: QuizDescription,
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int,
    @SerializedName("period_time")
    @ColumnInfo(name = "period_time")
    val periodTime: Int,
    @SerializedName("sort")
    @ColumnInfo(name = "sort")
    val sort: Int,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String
){
    var isSelected = false
    var uriPath = ""
        get() {
            return when (quizDescription.format) {
                FORMAT_VIDEO -> title + MIM_TYPE_VIDEO
                else -> title + MIM_TYPE_AUDIO
            }
        }
}