package com.vesam.quiz.data.api

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
        periodTime: Int,
        userAnswer: ArrayList<Int>
    ) =
        try {
            apiService.initSetQuizResult(userUuid, userApiToken, quizId, periodTime, userAnswer)
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