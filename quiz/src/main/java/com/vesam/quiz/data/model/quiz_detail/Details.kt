package com.vesam.quiz.data.model.quiz_detail


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Details(
    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String,
    @SerializedName("how_display_correct_answer")
    @ColumnInfo(name = "how_display_correct_answer")
    val howDisplayCorrectAnswer: String,
    @SerializedName("id")
    val id: Int=-1,
    @SerializedName("is_active")
    @ColumnInfo(name = "is_active")
    val isActive: Int,
    @SerializedName("jalali_created_at")
    @ColumnInfo(name = "jalali_created_at")
    val jalaliCreatedAt: String,
    @SerializedName("period_time")
    @ColumnInfo(name = "period_time")
    val periodTime: Int,
    @SerializedName("question_description")
    @ColumnInfo(name = "question_description")
    val questionDescription: Description,
    @SerializedName("pass_condition")
    @ColumnInfo(name = "pass_condition")
    val passCondition: Int,
    @SerializedName("slide_image")
    @ColumnInfo(name = "slide_image")
    val slideImage: String,
    @SerializedName("sort")
    @ColumnInfo(name = "sort")
    val sort: Int,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,
    @SerializedName("type")
    @ColumnInfo(name = "type")
    val type: String
)