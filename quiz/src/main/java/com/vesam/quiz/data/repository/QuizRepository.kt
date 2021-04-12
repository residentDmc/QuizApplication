package com.vesam.quiz.data.repository

import com.vesam.quiz.data.api.ApiHelper

class QuizRepository(private val apiHelper: ApiHelper) {

    suspend fun initQuizDetail(userUuid: String,userApiToken: String,quizId: Int) = apiHelper.initQuizDetail(userUuid, userApiToken, quizId)
    suspend fun initSetQuizResult(userUuid: String,userApiToken: String,quizId:Int,userAnswer:String) = apiHelper.initSetQuizResult(userUuid, userApiToken, quizId, userAnswer)
    suspend fun initGetQuizResult(userUuid: String,userApiToken: String,quizId: Int) = apiHelper.initGetQuizResult(userUuid, userApiToken, quizId)
}