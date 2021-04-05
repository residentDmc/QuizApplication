package com.vesam.quiz.data.model.quiz_list


import com.google.gson.annotations.SerializedName
import com.vesam.quiz.data.model.quiz_list.Meta
import com.vesam.quiz.data.model.quiz_list.Quiz

data class ResponseQuizListModel(
    @SerializedName("data")
    val quizList: List<Quiz>
)