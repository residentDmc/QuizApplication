package com.vesam.quiz.ui.view.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_detail.Answer
import com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel
import com.vesam.quiz.databinding.FragmentClozeBinding
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.ui.view.adapter.question_cloze_list.QuestionClozeAdapter
import com.vesam.quiz.ui.viewmodel.QuizViewModel
import com.vesam.quiz.utils.application.AppQuiz
import com.vesam.quiz.utils.build_config.BuildConfig
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_USER_ANSWER_LIST_ID
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_API_TOKEN_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_QUIZ_ID_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_UUID_VALUE
import com.vesam.quiz.utils.extention.initTick
import com.vesam.quiz.utils.tools.GlideTools
import com.vesam.quiz.utils.tools.HandelErrorTools
import com.vesam.quiz.utils.tools.ThrowableTools
import com.vesam.quiz.utils.tools.ToastTools
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class ClozeFragment : Fragment() {
    private lateinit var binding: FragmentClozeBinding
    private lateinit var timer: CountDownTimer
    private val toastTools: ToastTools by inject()
    private val glideTools: GlideTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val handelErrorTools: HandelErrorTools by inject()
    private val questionClozeAdapter: QuestionClozeAdapter by inject()
    private val gson: Gson by inject()
    private val quizViewModel: QuizViewModel by viewModel()
    private val resultAnswerListId: ArrayList<Int> = ArrayList()

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
        initAdapter()
        initOnClick()
        initRequestQuiz()
        initOnBackPress()
    }

    private fun initAdapter() {
        binding.rcCloze.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rcCloze.setHasFixedSize(true)
        binding.rcCloze.adapter = questionClozeAdapter
        questionClozeAdapter.setOnItemClickListener(object : OnClickListenerAny {
            override fun onClickListener(any: Any) =
                initOnItemClick(any)
        })
        questionClozeAdapter.setOnItemCountClickListener(object : OnClickListenerAny {
            override fun onClickListener(any: Any) =
                initItemCountClick(any)
        })
    }

    private fun initItemCountClick(any: Any) {
        val answer: Answer = any as Answer
        when {
            answer.isSelected -> resultAnswerListId.add(answer.id)
            else -> initRemoveId(answer.id)
        }
        initShowSubmitBtn(resultAnswerListId.size)

    }

    private fun initShowSubmitBtn(size: Int) = when (questionClozeAdapter.itemCount) {
        size -> binding.btnSubmit.visibility=View.VISIBLE
        else -> binding.btnSubmit.visibility=View.GONE
    }

    private fun initRemoveId(id: Int) {
        if (resultAnswerListId.contains(id))
            resultAnswerListId.remove(id)
    }

    private fun initOnItemClick(any: Any) {
        val answer: Answer = any as Answer
        initUpdateAdapter(answer)
    }

    private fun initUpdateAdapter(it: Answer) {
        questionClozeAdapter.updateIndexList(it)
    }

    private fun initOnClick() {
        binding.lnClozeImageLayout.lnImage.setOnClickListener { initFullScreenImage() }
        binding.btnSubmit.setOnClickListener { initSubmit() }
    }

    private fun initSubmit() {
        initCancelTimer()
        initResultFragment()
    }

    private fun initResultFragment() {
        val executor = Executors.newSingleThreadScheduledExecutor()
        executor.schedule({
            activity?.runOnUiThread {
                NavHostFragment.findNavController(this)
                    .navigate(R.id.resultClozeFragment, initBundle())
            }
        }, 1, TimeUnit.MILLISECONDS)
    }

    private fun initBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(BUNDLE_USER_ANSWER_LIST_ID, gson.toJson(resultAnswerListId))
        return bundle
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
    }

    private fun initHideLoading() {
        binding.lnParent.visibility = View.VISIBLE
    }

    private fun initResultQuiz(it: Any) {
        initHideLoading()
        when (it) {
            is ResponseQuizDetailModel -> initClozeQuiz(it)
            is Throwable -> initThrowable(it)
        }
    }

    private fun initThrowable(it: Throwable) {
        val message = throwableTools.getThrowableError(it)
        handelErrorTools.handelError(it)
        toastTools.toast(message)
    }

    private fun initClozeQuiz(it: ResponseQuizDetailModel) {
        initLoadImageQuestion(it)
        initUpdateAdapter(it)
        initPeriod(it.details.periodTime)
    }

    private fun initPeriod(it: Int) {
        binding.progressClozeTest.max = it
        binding.progressClozeTest.progress = it
        timer = object : CountDownTimer((it * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) =
                initTick(millisUntilFinished, binding.progressClozeTest)
            override fun onFinish() = initFinish()
        }
        timer.start()
    }

    private fun initFinish() {
        initCancelTimer()
        initResultFragment()
    }

    private fun initUpdateAdapter(it: ResponseQuizDetailModel) {
        questionClozeAdapter.updateList(it.questions)
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
        initCancelTimer()
        AppQuiz.activity.finish()
    }

    private fun initCancelTimer() {
        if (this::timer.isInitialized)
            timer.cancel()
    }
}