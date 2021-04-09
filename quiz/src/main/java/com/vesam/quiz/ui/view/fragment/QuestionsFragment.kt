package com.vesam.quiz.ui.view.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.danikula.videocache.HttpProxyCacheServer
import com.vesam.quiz.data.model.quiz_detail.Answer
import com.vesam.quiz.data.model.quiz_detail.Question
import com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel
import com.vesam.quiz.databinding.FragmentQuestionsBinding
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.ui.view.adapter.answer_list.AnswerAdapter
import com.vesam.quiz.ui.viewmodel.QuizViewModel
import com.vesam.quiz.utils.application.AppQuiz
import com.vesam.quiz.utils.build_config.BuildConfig
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_AUDIO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_TEXT
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_VIDEO
import com.vesam.quiz.utils.extention.checkPersianCharacter
import com.vesam.quiz.utils.extention.getProxy
import com.vesam.quiz.utils.extention.initTick
import com.vesam.quiz.utils.tools.GlideTools
import com.vesam.quiz.utils.tools.HandelErrorTools
import com.vesam.quiz.utils.tools.ThrowableTools
import com.vesam.quiz.utils.tools.ToastTools
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit


class QuestionsFragment : Fragment() {
    private lateinit var binding: FragmentQuestionsBinding
    private val navController: NavController by inject()
    private val toastTools: ToastTools by inject()
    private val glideTools: GlideTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val handelErrorTools: HandelErrorTools by inject()
    private val quizViewModel: QuizViewModel by inject()
    private val answerAdapter: AnswerAdapter by inject()
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
        binding.btnNextQuestion.setOnClickListener {  initQuestion() }
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
        initQuestion()
    }

    private fun initQuestion() {
        val question: Question=questionList.first()
        questionList.removeFirst()
        initCancelTimer()
        initStateQuestionFormat(question)
        checkPersianCharacter(question.title, binding.txtQuestion)
        initHideAnswerQuestion()
        answerAdapter.updateList(question.answers)
    }

    private fun initHideAnswerQuestion() {
        binding.btnNextQuestion.visibility=View.GONE
        binding.txtDescriptionAnswer.visibility=View.GONE
        binding.cvVideo.visibility=View.GONE
    }

    private fun initStateQuestionFormat(question: Question) = when (question.quizDescription.format) {
        FORMAT_TEXT -> initQuestionFormatText(question)
        FORMAT_VIDEO -> initQuestionFormatVideo(question)
        FORMAT_AUDIO -> initQuestionFormatAudio(question)
        else -> initQuestionFormatImage(question)
    }

    private fun initQuestionFormatImage(question: Question) {
        initShowQuestionFormatImage()
        initPeriodImageTime(question)
        glideTools.displayImageOriginal(binding.imgQuestion,question.quizDescription.urlContent)
    }

    private fun initQuestionFormatAudio(question: Question) {

    }

    private fun initQuestionFormatVideo(question: Question) {

    }

    private fun initQuestionFormatText(question: Question) {
        initShowQuestionFormatText()
        initPeriodTextTime(question)
        binding.txtQuestion.text = question.quizDescription.content
    }

    private fun initShowQuestionFormatText() {
        binding.lnText.visibility=View.VISIBLE
        binding.lnImage.visibility=View.GONE
    }

    private fun initShowQuestionFormatImage() {
        binding.lnText.visibility=View.GONE
        binding.lnImage.visibility=View.VISIBLE
    }

    private fun initPeriodTextTime(question: Question) {
        binding.progressPeriodTextTime.max = question.periodTime
        binding.progressPeriodTextTime.progress = question.periodTime
        timer = object : CountDownTimer((question.periodTime * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) = initTick(millisUntilFinished,binding.progressPeriodTextTime)
            override fun onFinish() = initFinish()
        }
        timer.start()
    }

    private fun initPeriodImageTime(question: Question) {
        binding.progressPeriodImageTime.max = question.periodTime
        binding.progressPeriodImageTime.progress = question.periodTime
        timer = object : CountDownTimer((question.periodTime * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) = initTick(millisUntilFinished,binding.progressPeriodImageTime)
            override fun onFinish() = initFinish()
        }
        timer.start()
    }

    private fun initFinish() {
        initQuestion()
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
        initStateListFormat(answer)
    }

    private fun initUnSuccessAnswer(answer: Answer) {
        answerAdapter.answerUnSuccessQuestion(answer)
        initStateListFormat(answer)
    }

    private fun initStateListFormat(answer: Answer) = when (answer.description.format) {
        FORMAT_TEXT -> initListFormatText(answer)
        FORMAT_VIDEO -> initListFormatVideo(answer)
        FORMAT_AUDIO -> initListFormatAudio(answer)
        else -> initListFormatImage(answer)
    }

    private fun initListFormatText(answer: Answer) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        binding.txtDescriptionAnswer.visibility = View.VISIBLE
        binding.txtDescriptionAnswer.text = answer.description.content
    }

    private fun initListFormatVideo(answer: Answer) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        binding.cvVideo.visibility = View.VISIBLE
        initCancelTimer()
        initVideoView(answer.description.urlContent)
    }

    private fun initVideoView(content: String) {
        val proxy: HttpProxyCacheServer = getProxy(requireContext())
        val proxyUrl = proxy.getProxyUrl(content)
        binding.viewVideo.setVideoPath(proxyUrl)
        val mediaController = MediaController(requireContext())
        binding.viewVideo.setMediaController(mediaController)
        mediaController.setAnchorView(binding.viewVideo)
    }

    private fun initListFormatAudio(answer: Answer) {
    }

    private fun initListFormatImage(answer: Answer) {
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