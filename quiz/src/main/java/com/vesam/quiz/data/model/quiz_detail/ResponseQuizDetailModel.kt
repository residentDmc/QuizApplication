package com.vesam.quiz.data.model.quiz_detail


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.vesam.quiz.data.model.quiz_list.Quiz
import com.vesam.quiz.utils.build_config.BuildConfig

@Entity(tableName = BuildConfig.GET_QUIZ_WITH_DETAILS_ENTITY,
    foreignKeys = [ForeignKey(entity = Quiz::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id"),
        onDelete = ForeignKey.CASCADE
    )]
)
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