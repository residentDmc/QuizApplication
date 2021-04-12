package com.vesam.quiz.data.api

import com.vesam.quiz.utils.build_config.BuildConfig
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.PERIOD_TIME
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.QUIZ_ID
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_ANSWERS
import retrofit2.http.Field
import retrofit2.http.Header

interface ApiHelper {

    // quiz -----------------------------------
    suspend fun initQuizDetail(userUuid: String, userApiToken: String, quizId: Int): Any

    suspend fun initSetQuizResult(
        userUuid: String,
        userApiToken: String,
        quizId: Int,
        userAnswer: String
    ): Any

    suspend fun initGetQuizResult(userUuid: String, userApiToken: String, quizId: Int): Any
}