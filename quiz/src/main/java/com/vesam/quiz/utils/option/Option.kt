package com.vesam.quiz.utils.option

import android.content.Context
import android.content.Intent
import com.vesam.quiz.ui.view.activity.QuizActivity

class Option{

    companion object{
        const val BUNDLE_BASE_URL_VALUE = "base_url"
        const val BUNDLE_USER_UUID_VALUE = "user_uuid"
        const val BUNDLE_USER_API_TOKEN_VALUE = "user_api_token"
        fun start(context: Context,
                  baseUrl: String,
                  token: String,
                  userId: String) {
            val intent = Intent()
            intent.putExtra(BUNDLE_BASE_URL_VALUE,baseUrl)
            intent.putExtra(BUNDLE_USER_API_TOKEN_VALUE,token)
            intent.putExtra(BUNDLE_USER_UUID_VALUE,userId)
            intent.setClass(context, QuizActivity::class.java)
            context.startActivity(intent)
        }
    }
}