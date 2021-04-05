package com.vesam.quiz.data.model.quiz_list


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.vesam.quiz.data.model.quiz_detail.Details
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.GET_QUIZ_LIST_ENTITY

@Entity(tableName = GET_QUIZ_LIST_ENTITY)
data class Quiz(
    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @SerializedName("jalali_created_at")
    @ColumnInfo(name = "jalali_created_at")
    val jalaliCreatedAt: String,
    @SerializedName("pass_condition")
    @ColumnInfo(name = "pass_condition")
    val passCondition: Int,
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