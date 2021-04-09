package com.vesam.quiz

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.MediaController
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.danikula.videocache.HttpProxyCacheServer
import com.google.gson.Gson
import com.vesam.quiz.data.model.quiz_detail.Answer
import com.vesam.quiz.data.model.quiz_detail.Question
import com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel
import com.vesam.quiz.databinding.FragmentQuestionsBinding
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.ui.view.adapter.answer_list.AnswerAdapter
import com.vesam.quiz.ui.viewmodel.QuizViewModel
import com.vesam.quiz.utils.build_config.BuildConfig
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_QUESTION
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_AUDIO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_TEXT
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_VIDEO
import com.vesam.quiz.utils.extention.checkPersianCharacter
import com.vesam.quiz.utils.extention.getProxy
import com.vesam.quiz.utils.tools.HandelErrorTools
import com.vesam.quiz.utils.tools.ThrowableTools
import com.vesam.quiz.utils.tools.ToastTools
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit


class QuestionsFragment : Fragment() {
    private lateinit var binding: FragmentQuestionsBinding
    private val navController: NavController by inject()
    private val toastTools: ToastTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val handelErrorTools: HandelErrorTools by inject()
    private val quizViewModel: QuizViewModel by inject()
    private val answerAdapter: AnswerAdapter by inject()
    private val gson: Gson by inject()
    private val questionList: ArrayList<Question> = ArrayList()
    private lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionsBinding.inflate(layoutInflater)
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
        initRequestListQuiz()
        initOnBackPress()
    }

    private fun initOnClick() {
        binding.imgPlay.setOnClickListener { initPlay() }
    }

    private fun initPlay() {
        binding.imgPlay.visibility = View.GONE
        binding.viewVideo.start()
    }

    private fun initRequestListQuiz() {
        quizViewModel.initQuizDetail(
            BuildConfig.USER_UUID_VALUE,
            BuildConfig.USER_API_TOKEN_VALUE,
            BuildConfig.USER_QUIZ_ID_VALUE
        ).observe(
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
        questionList.addAll(it.questions)
        val question = getQuestion()
        questionList.remove(question)
        initQuestion(question)
    }

    private fun getQuestion(): Question {
        val strQuestion = requireArguments().getString(BUNDLE_QUESTION, "")
        return gson.fromJson(strQuestion, Question::class.java)
    }

    private fun initQuestion(question: Question) {
        checkPersianCharacter(question.title, binding.txtTitle)
        initPeriodTime(question)
        binding.txtTitle.text = question.title
        answerAdapter.updateList(question.answers)
    }

    private fun initPeriodTime(question: Question) {
        binding.progressPeriodTime.max = question.periodTime
        binding.progressPeriodTime.progress = question.periodTime
        timer = object : CountDownTimer((question.periodTime * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) = initTick(millisUntilFinished)
            override fun onFinish() = initFinish()
        }
        timer.start()
    }

    private fun initFinish() {

    }

    private fun initTick(millisUntilFinished: Long) {
        val second = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
        binding.progressPeriodTime.progress = second.toInt()
    }

    private fun initThrowable(it: Throwable) {
        val message = throwableTools.getThrowableError(it)
        handelErrorTools.handelError(it)
        toastTools.toast(message)
    }

    private fun initAdapter() {
        binding.rcQuestion.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rcQuestion.setHasFixedSize(true)
        binding.rcQuestion.adapter = answerAdapter
        answerAdapter.setOnItemClickListener(object : OnClickListenerAny {
            override fun onClickListener(any: Any) = initOnItemClick(any)
        })
    }

    private fun initOnItemClick(any: Any) {
        val answer: Answer = any as Answer
        when (answer.isCorrect) {
            1 -> initSuccessAnswer(answer)
            else -> initUnSuccessAnswer(answer)
        }
    }

    private fun initSuccessAnswer(answer: Answer) {
        answerAdapter.answerSuccessQuestion(answer)
        initStateFormat(answer)
    }

    private fun initUnSuccessAnswer(answer: Answer) {
        answerAdapter.answerUnSuccessQuestion(answer)
        initStateFormat(answer)
    }

    private fun initStateFormat(answer: Answer) = when (answer.description.format) {
        FORMAT_TEXT -> initFormatText(answer)
        FORMAT_VIDEO -> initFormatVideo(answer)
        FORMAT_AUDIO -> initFormatAudio(answer)
        else -> initFormatImage(answer)
    }

    private fun initFormatText(answer: Answer) {
        binding.lnAction.visibility = View.VISIBLE
        binding.txtDescriptionAnswer.visibility = View.VISIBLE
        binding.txtDescriptionAnswer.text = answer.description.content
    }

    private fun initFormatVideo(answer: Answer) {
        binding.lnAction.visibility = View.VISIBLE
        binding.cvVideo.visibility = View.VISIBLE
        initVideoView(answer.description.content)
    }

    private fun initVideoView(content: String) {
        val proxy: HttpProxyCacheServer = getProxy(requireContext())
        val proxyUrl = proxy.getProxyUrl("https://hajifirouz3.cdn.asset.aparat.com/aparat-video/02b56a8cfd45237d82bcfccbea8ebb1431923323-1080p.mp4?wmsAuthSign=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbiI6ImQzNDBiNjRhY2ZiYWQwNTc1ZWUxOWMyNDRlYzY4ZTc0IiwiZXhwIjoxNjE4MDA0NDUyLCJpc3MiOiJTYWJhIElkZWEgR1NJRyJ9.3uZsxf2RsY76BLHzJszBc511Yjv5q7ZydkuKxz3uFXk")
        binding.viewVideo.setVideoPath(proxyUrl)
        val mediaController = MediaController(requireContext())
        binding.viewVideo.setMediaController(mediaController)
        mediaController.setAnchorView(binding.viewVideo)
    }

    private fun initFormatAudio(answer: Answer) {
    }

    private fun initFormatImage(answer: Answer) {
    }


    private fun initOnBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = initOnBackPressed()
            })
    }

    private fun initOnBackPressed() {
        if (this::timer.isInitialized)
            timer.cancel()
        navController.popBackStack()
    }

}