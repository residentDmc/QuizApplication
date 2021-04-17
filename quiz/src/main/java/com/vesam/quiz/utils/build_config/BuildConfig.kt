package com.vesam.quiz.utils.build_config


class BuildConfig {
    companion object {
        var BASE_URL = ""
        var BASE_URL_IMAGE_AND_VIDEO_VALUE = ""

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
        const val BUNDLE_USER_QUESTION_LIST = "userQuestionList"
        const val BUNDLE_USER_ANSWER_LIST_ID = "userAnswerListId"
        const val BUNDLE_MULTIMEDIA = "multimedia"

        // state format
        const val FORMAT_TEXT = "text"
        const val FORMAT_VIDEO = "video"
        const val FORMAT_AUDIO = "audio"
        const val FORMAT_IMAGE = "image"

        // state type
        const val MULTIMEDIA = "multimedia"
        const val CLOZE = "cloze"

        // state question
        const val STEP_BY_STEP = "stepByStep"
        const val FINAL_LEVEL = "finalLevel"
        var HOW_DISPLAY_CORRECT_ANSWER = ""
        var PASS_CONDITION = -1
    }
}