package com.vesam.quiz.data.api

import com.vesam.quiz.data.model.get_quiz_result.ResponseGetQuizResultModel
import com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel
import com.vesam.quiz.data.model.set_quiz_result.ResponseSetQuizResultModel
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.GET_QUIZ_RESULT
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.PERIOD_TIME
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.QUIZ_DETAIL
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.QUIZ_ID
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.QUIZ_LIST
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.SET_QUIZ_RESULT
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_ANSWERS
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_API_TOKEN
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_UUID
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    //quiz -------------------------------

    @POST(QUIZ_DETAIL)
    @FormUrlEncoded
    suspend fun initQuizDetail(
        @Header(USER_UUID) userUuid: String,
        @Header(USER_API_TOKEN) userApiToken: String,
        @Field(QUIZ_ID) quizId: Int
    ): ResponseQuizDetailModel

    @POST(SET_QUIZ_RESULT)
    suspend fun initSetQuizResult(
        @Header(USER_UUID) userUuid: String,
        @Header(USER_API_TOKEN) userApiToken: String,
        @Field(QUIZ_ID) quizId: Int,
        @Field(PERIOD_TIME) periodTime: Int,
        @Field(USER_ANSWERS) userAnswer: ArrayList<Int>
    ): ResponseSetQuizResultModel

    @POST(GET_QUIZ_RESULT)
    suspend fun initGetQuizResult(
        @Header(USER_UUID) userUuid: String,
        @Header(USER_API_TOKEN) userApiToken: String,
        @Field(QUIZ_ID) quizId: Int
    ): ResponseGetQuizResultModel
}