package com.vesam.quiz.ui.view.activity

import android.os.Bundle
import com.vesam.quiz.R
import com.vesam.quiz.utils.base.BaseActivity
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BASE_URL
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BASE_URL_IMAGE_AND_VIDEO_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_API_TOKEN_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_QUIZ_ID_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_UUID_VALUE
import com.vesam.quiz.utils.option.Option
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_BASE_URL_IMAGE_AND_VIDEO_VALUE
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_BASE_URL_VALUE
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_QUIZ_ID_VALUE
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_USER_API_TOKEN_VALUE
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_USER_UUID_VALUE

class QuizActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        BASE_URL = intent.extras!!.getString(Option.BUNDLE_BASE_URL_VALUE,"")
        BASE_URL_IMAGE_AND_VIDEO_VALUE = intent.extras!!.getString(Option.BUNDLE_BASE_URL_IMAGE_AND_VIDEO_VALUE,"")
        USER_API_TOKEN_VALUE =intent.extras!!.getString(Option.BUNDLE_USER_API_TOKEN_VALUE,"")
        USER_UUID_VALUE=intent.extras!!.getString(Option.BUNDLE_USER_UUID_VALUE,"")
        USER_QUIZ_ID_VALUE=intent.extras!!.getInt(Option.BUNDLE_QUIZ_ID_VALUE,-1)
    }
}