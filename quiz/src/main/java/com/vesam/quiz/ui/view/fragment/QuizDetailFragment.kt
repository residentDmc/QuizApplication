package com.vesam.quiz.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_detail.Details
import com.vesam.quiz.data.model.quiz_detail.Question
import com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel
import com.vesam.quiz.databinding.FragmentQuizDetailBinding
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.ui.view.adapter.quiz_list.QuestionAdapter
import com.vesam.quiz.ui.viewmodel.QuizViewModel
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_QUESTION
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_API_TOKEN_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_QUIZ_ID_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_UUID_VALUE
import com.vesam.quiz.utils.extention.checkPersianCharacter
import com.vesam.quiz.utils.tools.HandelErrorTools
import com.vesam.quiz.utils.tools.ThrowableTools
import com.vesam.quiz.utils.tools.ToastTools
import org.koin.android.ext.android.inject


class QuizDetailFragment : Fragment() {

    private lateinit var binding: FragmentQuizDetailBinding
    private val navController: NavController by inject()
    private val toastTools: ToastTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val handelErrorTools: HandelErrorTools by inject()
    private val quizViewModel: QuizViewModel by inject()
    private val questionAdapter: QuestionAdapter by inject()
    private val gson: Gson by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizDetailBinding.inflate(layoutInflater)
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
        initAdapter()
        initRequestListQuiz()
    }

    private fun initRequestListQuiz() {
        quizViewModel.initQuizDetail(USER_UUID_VALUE, USER_API_TOKEN_VALUE, USER_QUIZ_ID_VALUE)
            .observe(
                requireActivity(),
                this::initResultListQuiz
            )
    }

    private fun initResultListQuiz(it: Any) {
        when (it) {
            is ResponseQuizDetailModel -> initQuizDetailModel(it)
            is Throwable -> initThrowable(it)
        }
    }

    private fun initQuizDetailModel(it: ResponseQuizDetailModel) {
        initDetail(it.details)
        questionAdapter.updateList(it.questions)
    }

    private fun initDetail(details: Details) {
        binding.txtTitle.text = details.title
        checkPersianCharacter(details.title, binding.txtTitle)
    }

    private fun initThrowable(it: Throwable) {
        val message = throwableTools.getThrowableError(it)
        handelErrorTools.handelError(it)
        toastTools.toast(message)
    }

    private fun initAdapter() {
        binding.rcQuiz.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rcQuiz.setHasFixedSize(true)
        binding.rcQuiz.adapter = questionAdapter
        questionAdapter.setOnItemClickListener(object : OnClickListenerAny {
            override fun onClickListener(any: Any) = initOnItemClick(any)
        })
    }

    private fun initOnItemClick(any: Any) {
        val question: Question = any as Question
        navController.navigate(R.id.action_quizDetailFragment_to_questionsFragment,initBundle(question))
    }

    private fun initBundle(question: Question): Bundle {
        val bundle=Bundle()
        bundle.putString(BUNDLE_QUESTION,gson.toJson(question))
        return bundle
    }


}