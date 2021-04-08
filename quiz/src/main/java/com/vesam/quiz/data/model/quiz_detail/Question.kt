package com.vesam.quiz.data.model.quiz_detail


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName


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