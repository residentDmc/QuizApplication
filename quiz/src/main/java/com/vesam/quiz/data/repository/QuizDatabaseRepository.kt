package com.vesam.quiz.data.repository

import com.vesam.quiz.data.database.QuizDatabase
import com.vesam.quiz.data.model.quiz_detail.Details
import com.vesam.quiz.data.model.quiz_list.Quiz

class QuizDatabaseRepository(private val quizDatabase: QuizDatabase) {

    suspend fun insertQuiz(quiz: Quiz) = quizDatabase.quizDAO().insertQuiz(quiz)
    suspend fun insertListQuiz(listQuiz: List<Quiz>) = quizDatabase.quizDAO().insertListQuiz(listQuiz)
    suspend fun updateQuiz(quiz: Quiz) = quizDatabase.quizDAO().updateQuiz(quiz)
    suspend fun getListQuiz() = quizDatabase.quizDAO().getListQuiz()
    suspend fun deleteQuiz() = quizDatabase.quizDAO().delete()
}