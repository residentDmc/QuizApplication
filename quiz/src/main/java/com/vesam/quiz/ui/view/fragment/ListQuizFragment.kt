package com.vesam.quiz.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vesam.quiz.data.model.quiz_list.Quiz
import com.vesam.quiz.databinding.FragmentListQuizBinding
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.ui.view.adapter.quiz_list.QuizAdapter
import com.vesam.quiz.ui.viewmodel.QuizViewModel
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_API_TOKEN_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_UUID_VALUE
import com.vesam.quiz.utils.tools.HandelErrorTools
import com.vesam.quiz.utils.tools.ThrowableTools
import com.vesam.quiz.utils.tools.ToastTools
import org.koin.android.ext.android.inject


class ListQuizFragment : Fragment() {

    private lateinit var binding: FragmentListQuizBinding
    private val navController: NavController by inject()
    private val toastTools: ToastTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val handelErrorTools: HandelErrorTools by inject()
    private val quizViewModel: QuizViewModel by inject()
    private val quizAdapter: QuizAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListQuizBinding.inflate(layoutInflater)
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
        quizViewModel.initQuizList(USER_UUID_VALUE, USER_API_TOKEN_VALUE).observe(
            requireActivity(),
            this::initResultListQuiz
        )
    }

    private fun initResultListQuiz(it: Any) {
        when (it) {
            is List<*> -> initQuizListModel(it)
            is Throwable -> initThrowable(it)
        }
    }

    private fun initQuizListModel(it: List<*>) {
        val listQuiz: List<Quiz> = it.filterIsInstance<Quiz>()
        quizAdapter.updateList(listQuiz)
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
        binding.rcQuiz.adapter = quizAdapter
        quizAdapter.setOnItemClickListener(object : OnClickListenerAny {
            override fun onClickListener(any: Any) = initOnItemClick(any)
        })
    }

    private fun initOnItemClick(any: Any) {
        val quiz: Quiz = any as Quiz
        quizViewModel.initQuizDetail(USER_UUID_VALUE, USER_API_TOKEN_VALUE,quiz.id).observe(
            requireActivity(),
            this::initResultQuizDetail
        )
    }

    private fun initResultQuizDetail(it: Any) {
        when (it) {
            is Throwable -> initThrowable(it)
        }
    }
}