package com.vesam.quiz.data.model.quiz_detail


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.SerializedName
import com.vesam.quiz.data.model.quiz_list.Quiz
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.GET_QUIZ_WITH_DETAILS_ENTITY

@Entity(tableName = GET_QUIZ_WITH_DETAILS_ENTITY,
    foreignKeys = [ForeignKey(entity = Quiz::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id"),
        onDelete = CASCADE)]
)
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
    @ColumnInfo(name = "id")
    val id: Int,
    @SerializedName("is_active")
    @ColumnInfo(name = "is_active")
    val isActive: Int,
    @SerializedName("jalali_created_at")
    @ColumnInfo(name = "jalali_created_at")
    val jalaliCreatedAt: String,
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