package com.vesam.quiz.utils.build_config


class BuildConfig {
    companion object {
        var BASE_URL = ""

        // header
        const val CONTENT_TYPE_HEADER = "Content-Type"
        const val APPLICATION_JSON_HEADER = "application/json"
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer "

        // header
        const val USER_UUID = "user_uuid"
        const val USER_API_TOKEN = "user_api_token"

        // header value
        var USER_UUID_VALUE = ""
        var USER_API_TOKEN_VALUE = ""
        var USER_QUIZ_ID_VALUE = -1


        // form_data
        const val QUIZ_LIST = "quiz/get-quiz-list"
        const val QUIZ_DETAIL = "quiz/get-quiz-with-details"
        const val SET_QUIZ_RESULT = "quiz/set-quiz-result"
        const val GET_QUIZ_RESULT = "quiz/get-quiz-result"

        const val QUIZ_ID = "quiz_id"
        const val PERIOD_TIME = "period_time"
        const val USER_ANSWERS = "user_answers"

        // database
        const val QUIZ_DATABASE = "quiz_database"

        // table
        const val DETAILS_ENTITY = "details_entity"
        const val SET_QUIZ_RESULT_ENTITY = "set_quiz_result_entity"
        const val GET_QUIZ_RESULT_ENTITY = "get_quiz_result_entity"

        // bundle
        const val BUNDLE_QUESTION = "question"

        // state format
        const val FORMAT_TEXT = "text"
        const val FORMAT_VIDEO = "video"
        const val FORMAT_AUDIO = "audio"
        const val FORMAT_IMAGE = "image"
    }
}