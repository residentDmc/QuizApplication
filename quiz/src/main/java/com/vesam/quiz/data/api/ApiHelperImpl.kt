package com.vesam.quiz.data.api

import okhttp3.ResponseBody
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    // quiz ----------------------------------
    override suspend fun initQuizDetail(userUuid: String, userApiToken: String, quizId: Int) =
        try {
            apiService.initQuizDetail(userUuid, userApiToken, quizId)
        } catch (e: Exception) {
            e
        }

    override suspend fun initSetQuizResult(
        userUuid: String, userApiToken: String,
        quizId: Int,
        userAnswer: String
    ) =
        try {
            apiService.initSetQuizResult(userUuid, userApiToken, quizId, userAnswer)
        } catch (e: Exception) {
            e
        }

    override suspend fun initGetQuizResult(userUuid: String, userApiToken: String, quizId: Int) =
        try {
            apiService.initGetQuizResult(userUuid, userApiToken, quizId)
        } catch (e: Exception) {
            e
        }

}