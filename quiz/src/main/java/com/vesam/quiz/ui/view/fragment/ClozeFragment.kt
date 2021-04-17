package com.vesam.quiz.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.gson.Gson
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel
import com.vesam.quiz.databinding.FragmentClozeBinding
import com.vesam.quiz.ui.viewmodel.QuizViewModel
import com.vesam.quiz.utils.application.AppQuiz
import com.vesam.quiz.utils.build_config.BuildConfig
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.CLOZE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_API_TOKEN_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_QUIZ_ID_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_UUID_VALUE
import com.vesam.quiz.utils.tools.GlideTools
import com.vesam.quiz.utils.tools.HandelErrorTools
import com.vesam.quiz.utils.tools.ThrowableTools
import com.vesam.quiz.utils.tools.ToastTools
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ClozeFragment : Fragment() {
    private lateinit var binding: FragmentClozeBinding
    private val toastTools: ToastTools by inject()
    private val glideTools: GlideTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val handelErrorTools: HandelErrorTools by inject()
    private val gson: Gson by inject()
    private val quizViewModel: QuizViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClozeBinding.inflate(layoutInflater)
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
        initOnClick()
        initRequestQuiz()
        initOnBackPress()
    }

    private fun initOnClick() {
        binding.lnClozeImageLayout.lnImage.setOnClickListener { initFullScreenImage() }
    }

    private fun initFullScreenImage() {
        val urlContent: String = binding.lnClozeImageLayout.imgCloze.tag as String
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentFullscreenSlider = FragmentFullscreenSlider()
        val transaction = fragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.add(android.R.id.content, fragmentFullscreenSlider).addToBackStack(null)
            .commit()
        fragmentFullscreenSlider.setImage(urlContent)
    }

    private fun initRequestQuiz() {
        initShowLoading()
        quizViewModel.initQuizDetail(
            USER_UUID_VALUE,
            USER_API_TOKEN_VALUE,
            USER_QUIZ_ID_VALUE
        ).observe(
            viewLifecycleOwner,
            this::initResultQuiz
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
        CLOZE -> initClozeQuiz(it)
        else -> initNotCloze()
    }

    private fun initNotCloze() {
        toastTools.toast(resources.getString(R.string.not_cloze))
        initOnBackPressed()
    }

    private fun initClozeQuiz(it: ResponseQuizDetailModel) {
        initLoadImageQuestion(it)
    }

    private fun initLoadImageQuestion(it: ResponseQuizDetailModel) {
        binding.lnClozeImageLayout.imgCloze.tag = it.details.questionDescription.urlContent
        glideTools.displayImageOriginal(
            binding.lnClozeImageLayout.imgCloze,
            it.details.questionDescription.urlContent
        )
    }

    private fun initOnBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = initOnBackPressed()
            })
    }

    private fun initOnBackPressed() {
        AppQuiz.activity.finish()
    }
}