package com.vesam.quiz.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vesam.quiz.data.model.quiz_list.ResponseQuizListModel
import com.vesam.quiz.data.repository.QuizDatabaseRepository
import com.vesam.quiz.data.repository.QuizRepository
import com.vesam.quiz.utils.abstracts.BaseViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuizViewModel(
    private val quizRepository: QuizRepository,
    private val quizDatabaseRepository: QuizDatabaseRepository
) : BaseViewModel() {

    fun initQuizList(userUuid: String,userApiToken: String): LiveData<Any> {
        val liveData = MutableLiveData<Any>()
        viewModelScope.launch {
            val listQuiz=quizDatabaseRepository.getListQuiz()
            when {
                listQuiz.isNotEmpty() -> liveData.postValue(listQuiz)
                else -> initRequestQuizList(userUuid, userApiToken,liveData)
            }
        }
        return liveData
    }

    private suspend fun initRequestQuizList(
        userUuid: String,
        userApiToken: String,
        liveData: MutableLiveData<Any>
    ) {
        withContext(IO) { quizRepository.initQuizList(userUuid, userApiToken) }.let{
            when (it) {
                is ResponseQuizListModel -> {
                    quizDatabaseRepository.insertListQuiz(it.quizList)
                    liveData.postValue(it.quizList)
                }
                else -> liveData.postValue(it)
            }
        }
    }

    fun initQuizDetail(userUuid: String,userApiToken: String,quizId: Int): LiveData<Any> {
        val mutableLiveDataVersionApp = MutableLiveData<Any>()
        viewModelScope.launch {
            withContext(IO) { quizRepository.initQuizDetail(userUuid, userApiToken, quizId) }.let(mutableLiveDataVersionApp::postValue)
        }
        return mutableLiveDataVersionApp
    }

    fun initSetQuizResult(userUuid: String,userApiToken: String,quizId:Int,periodTime:Int,userAnswer:ArrayList<Int>): LiveData<Any> {
        val mutableLiveDataVersionApp = MutableLiveData<Any>()
        viewModelScope.launch {
            withContext(IO) { quizRepository.initSetQuizResult(userUuid, userApiToken, quizId, periodTime, userAnswer) }.let(mutableLiveDataVersionApp::postValue)
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