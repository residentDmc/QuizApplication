package com.vesam.quiz.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel
import com.vesam.quiz.data.repository.QuizDetailDatabaseRepository
import com.vesam.quiz.data.repository.QuizRepository
import com.vesam.quiz.utils.abstracts.BaseViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuizViewModel(
    private val quizRepository: QuizRepository,
    private val quizDetailDatabaseRepository: QuizDetailDatabaseRepository
) : BaseViewModel() {

    fun initQuizDetail(userUuid: String,userApiToken: String,quizId: Int): LiveData<Any> {
        val liveData = MutableLiveData<Any>()
        viewModelScope.launch {
            when (val detail=quizDetailDatabaseRepository.getDetails(quizId)) {
                null -> initRequestQuizDetail(userUuid, userApiToken,quizId,liveData)
                else -> liveData.postValue(detail)
            }
        }
        return liveData
    }

    private suspend fun initRequestQuizDetail(
        userUuid: String,
        userApiToken: String,
        quizId: Int,
        liveData: MutableLiveData<Any>
    ) {
        withContext(IO) { quizRepository.initQuizDetail(userUuid, userApiToken, quizId) }.let{
            when (it) {
                is ResponseQuizDetailModel -> {
                    it.id=it.details.id
                    quizDetailDatabaseRepository.insertDetails(it)
                    liveData.postValue(it)
                }
                else -> liveData.postValue(it)
            }
        }
    }

    fun initSetQuizResult(userUuid: String,userApiToken: String,quizId:Int,userAnswer:String): LiveData<Any> {
        val mutableLiveDataVersionApp = MutableLiveData<Any>()
        viewModelScope.launch {
            withContext(IO) { quizRepository.initSetQuizResult(userUuid, userApiToken, quizId, userAnswer) }.let(mutableLiveDataVersionApp::postValue)
        }
        return mutableLiveDataVersionApp
    }

    fun initGetQuizResult(userUuid: String,userApiToken: String,quizId: Int): LiveData<Any> {
        val mutableLiveDataVersionApp = MutableLiveData<Any>()
        viewModelScope.launch {
            withContext(IO) { quizRepository.initGetQuizResult(userUuid, userApiToken, quizId) }.let(mutableLiveDataVersionApp::postValue)
        }
        return mutableLiveDataVersionApp
    }

}