package com.vesam.quiz.ui.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.google.gson.Gson
import com.vesam.quiz.data.model.set_quiz_result.ResponseSetQuizResultModel
import com.vesam.quiz.databinding.FragmentResultClozeBinding
import com.vesam.quiz.ui.viewmodel.QuizViewModel
import com.vesam.quiz.utils.application.AppQuiz
import com.vesam.quiz.utils.base.BaseActivity
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_USER_ANSWER_LIST_ID
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_API_TOKEN_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_QUIZ_ID_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_UUID_VALUE
import com.vesam.quiz.utils.tools.HandelErrorTools
import com.vesam.quiz.utils.tools.ThrowableTools
import com.vesam.quiz.utils.tools.ToastTools
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ResultClozeFragment : Fragment() {
    private lateinit var binding: FragmentResultClozeBinding
    private val handelErrorTools: HandelErrorTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val toastTools: ToastTools by inject()
    private val gson: Gson by inject()
    private val quizViewModel: QuizViewModel by viewModel()
    private val resultAnswerListId: ArrayList<Int> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultClozeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initAction()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun initAction() {
        initBundle()
        initRequestSetQuizResult()
        initOnClick()
        initOnBackPress()
    }

    private fun initBundle() {
        val userQuestionList = requireArguments().getString(BUNDLE_USER_ANSWER_LIST_ID, "")
        val answerList = gson.fromJson(userQuestionList, Array<Int>::class.java).asList()
        resultAnswerListId.clear()
        resultAnswerListId.addAll(answerList)
    }

    private fun initOnBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = initFinishActivity()
            })
    }

    private fun initFinishActivity() {
        AppQuiz.activity.finish()
    }


    private fun initRequestSetQuizResult() {
        val userAnswerListId = requireArguments().getString(BUNDLE_USER_ANSWER_LIST_ID, "")
        initShowLoading()
        quizViewModel.initSetQuizResult(
            USER_UUID_VALUE,
            USER_API_TOKEN_VALUE,
            USER_QUIZ_ID_VALUE,
            userAnswerListId
        ).observe(
            viewLifecycleOwner,
            this::initResultSetQuiz
        )
    }

    private fun initShowLoading() {
        binding.lnParent.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun initHideLoading() {
        binding.lnParent.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    private fun initResultSetQuiz(it: Any) {
        initHideLoading()
        when (it) {
            is ResponseSetQuizResultModel -> initSetQuizModel(it)
            is Throwable -> initThrowable(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initSetQuizModel(it: ResponseSetQuizResultModel) {
        binding.txtSuccessAnswer.text = it.quiz.result.correctAnswersCount.toString()
        binding.txtUnSuccessAnswer.text = it.quiz.result.incorrectAnswersCount.toString()
        binding.progressSuccess.max = it.quiz.result.questionsCount
        binding.progressUnSuccess.max = it.quiz.result.questionsCount
        binding.progressSuccess.progress = it.quiz.result.correctAnswersCount
        binding.progressUnSuccess.progress = it.quiz.result.incorrectAnswersCount
        binding.txtSuccessQuestionResult.text =
            """${it.quiz.result.correctAnswersCount} / ${it.quiz.result.questionsCount}"""
    }

    private fun initThrowable(it: Throwable) {
        val message = throwableTools.getThrowableError(it)
        handelErrorTools.handelError(it)
        toastTools.toast(message)
    }

    private fun initOnClick() {
        binding.btnRetest.setOnClickListener { initFinishActivity() }
    }
}