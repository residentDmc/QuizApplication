package com.vesam.quiz.data.model.quiz_detail


import com.google.gson.annotations.SerializedName

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
}