package com.vesam.quiz.data.model.quiz_detail


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.google.gson.annotations.SerializedName
import com.vesam.quiz.data.model.quiz_list.Quiz
import com.vesam.quiz.utils.build_config.BuildConfig
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.GET_QUIZ_WITH_DETAILS_ENTITY


data class Question(
    @SerializedName("answers")
    @ColumnInfo(name = "answers")
    val answers: List<Answer>,
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
)