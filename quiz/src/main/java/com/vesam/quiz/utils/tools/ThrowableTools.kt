package com.vesam.quiz.utils.tools

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vesam.quiz.R
import com.vesam.quiz.data.model.error_model.ResponseErrorModel
import com.vesam.quiz.utils.application.AppQuiz
import retrofit2.HttpException
import java.net.SocketTimeoutException


class ThrowableTools(private val networkTools: NetworkTools,private val gson: Gson) {

    fun getThrowableError(throwable: Throwable): String {
        return initResultException(throwable)
    }

    private fun initResultException(throwable: Throwable): String {
        return when {
            networkTools.isNetworkAvailable -> AppQuiz.context.getString(R.string.no_connection)
            throwable is HttpException -> initHttpException(throwable)
            throwable is SocketTimeoutException -> AppQuiz.context.getString(R.string.time_out)
            else -> throwable.message.toString()
        }
    }

    private fun initHttpException(throwable: Throwable): String {
        val responseBody = (throwable as HttpException).response()!!.errorBody()
        return try {
            val type = object : TypeToken<ResponseErrorModel>() {}.type
            val responseErrorResponse: ResponseErrorModel? =
                gson.fromJson(responseBody!!.charStream(), type)
            return responseErrorResponse?.message.toString()
        }catch (e:Exception){
            throwable.message()
        }
    }
}