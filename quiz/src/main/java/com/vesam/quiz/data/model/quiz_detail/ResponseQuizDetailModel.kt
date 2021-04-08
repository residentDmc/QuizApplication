package com.vesam.quiz.data.model.quiz_detail


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.DETAILS_ENTITY

@Entity(tableName = DETAILS_ENTITY)
data class ResponseQuizDetailModel(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int=-1,
    @SerializedName("details")
    val details: Details,
    @SerializedName("questions")
    val questions: List<Question>
)