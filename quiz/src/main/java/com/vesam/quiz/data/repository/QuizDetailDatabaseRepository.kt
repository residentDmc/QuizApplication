package com.vesam.quiz.data.repository

import com.vesam.quiz.data.database.QuizDatabase
import com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel

class QuizDetailDatabaseRepository(private val quizDatabase: QuizDatabase) {

    suspend fun insertDetails(details: ResponseQuizDetailModel) = quizDatabase.detailsDAO().insertDetails(details)
    suspend fun updateDetails(details: ResponseQuizDetailModel) = quizDatabase.detailsDAO().updateDetails(details)
    suspend fun getDetails(id: Int) = quizDatabase.detailsDAO().getDetails(id)

}