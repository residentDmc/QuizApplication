package com.vesam.quiz.ui.view.activity

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel
import com.vesam.quiz.databinding.ActivityQuizBinding
import com.vesam.quiz.ui.viewmodel.QuizViewModel
import com.vesam.quiz.utils.application.AppQuiz
import com.vesam.quiz.utils.base.BaseActivity
import com.vesam.quiz.utils.build_config.BuildConfig
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BASE_URL
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BASE_URL_IMAGE_AND_VIDEO_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MULTIMEDIA
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_API_TOKEN_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_QUIZ_ID_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_UUID_VALUE
import com.vesam.quiz.utils.option.Option
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_BASE_URL_IMAGE_AND_VIDEO_VALUE
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_BASE_URL_VALUE
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_QUIZ_ID_VALUE
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_USER_API_TOKEN_VALUE
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_USER_UUID_VALUE
import com.vesam.quiz.utils.tools.HandelErrorTools
import com.vesam.quiz.utils.tools.ThrowableTools
import com.vesam.quiz.utils.tools.ToastTools
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class QuizActivity : BaseActivity() {

    private lateinit var binding: ActivityQuizBinding
    private lateinit var navController: NavController
    private val toastTools: ToastTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val handelErrorTools: HandelErrorTools by inject()
    private val quizViewModel: QuizViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            initAction()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun initAction() {
        initBundle()
        initNavController()
        initRequest()
    }

    private fun initBundle() {
        BASE_URL = intent.extras!!.getString(BUNDLE_BASE_URL_VALUE,"")
        BASE_URL_IMAGE_AND_VIDEO_VALUE = intent.extras!!.getString(
            BUNDLE_BASE_URL_IMAGE_AND_VIDEO_VALUE,"")
        USER_API_TOKEN_VALUE =intent.extras!!.getString(BUNDLE_USER_API_TOKEN_VALUE,"")
        USER_UUID_VALUE=intent.extras!!.getString(BUNDLE_USER_UUID_VALUE,"")
        USER_QUIZ_ID_VALUE=intent.extras!!.getInt(BUNDLE_QUIZ_ID_VALUE,-1)
    }

    private fun initNavController() {
        navController=Navigation.findNavController(AppQuiz.activity, R.id.my_nav_fragment)
    }

    private fun initRequest() {
        initShowLoading()
        quizViewModel.initQuizDetail(
            USER_UUID_VALUE,
            USER_API_TOKEN_VALUE,
            USER_QUIZ_ID_VALUE
        ).observe(
            this,
            this::initResultQuiz
        )
    }

    private fun initShowLoading() {
        binding.lnNavFragment.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun initHideLoading() {
        binding.lnNavFragment.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    private fun initResultQuiz(it: Any) {
        initHideLoading()
        when (it) {
            is ResponseQuizDetailModel -> initQuizDetailModel(it)
            is Throwable -> initThrowable(it)
        }
    }

    private fun initThrowable(it: Throwable) {
        val message = throwableTools.getThrowableError(it)
        handelErrorTools.handelError(it)
        toastTools.toast(message)
    }

    private fun initQuizDetailModel(it: ResponseQuizDetailModel) = when (it.details.type) {
        MULTIMEDIA -> initMultimediaQuiz()
        else -> initClozeQuiz()
    }

    private fun initClozeQuiz() {
        navController.setGraph(R.navigation.nav_graph_cloze)
    }

    private fun initMultimediaQuiz() {
        navController.setGraph(R.navigation.nav_graph_quiz)
    }
}