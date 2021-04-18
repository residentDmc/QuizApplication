package com.vesam.quiz.ui.view.fragment

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.danikula.videocache.HttpProxyCacheServer
import com.google.gson.Gson
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_detail.Answer
import com.vesam.quiz.data.model.quiz_detail.Question
import com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel
import com.vesam.quiz.databinding.FragmentQuestionsBinding
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.ui.view.adapter.answer_quiz_list.AnswerAdapter
import com.vesam.quiz.ui.viewmodel.QuizViewModel
import com.vesam.quiz.utils.application.AppQuiz
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_USER_ANSWER_LIST_ID
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_USER_QUESTION_LIST
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_AUDIO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_TEXT
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_VIDEO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.HOW_DISPLAY_CORRECT_ANSWER
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MULTIMEDIA
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.PASS_CONDITION
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.STEP_BY_STEP
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_API_TOKEN_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_QUIZ_ID_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_UUID_VALUE
import com.vesam.quiz.utils.extention.checkPersianCharacter
import com.vesam.quiz.utils.extention.getProxy
import com.vesam.quiz.utils.extention.initTick
import com.vesam.quiz.utils.tools.GlideTools
import com.vesam.quiz.utils.tools.HandelErrorTools
import com.vesam.quiz.utils.tools.ThrowableTools
import com.vesam.quiz.utils.tools.ToastTools
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class QuestionsFragment : Fragment() {
    private lateinit var binding: FragmentQuestionsBinding
    private lateinit var mediaPlayerQuestion: MediaPlayer
    private lateinit var mediaPlayerAnswer: MediaPlayer
    private lateinit var timer: CountDownTimer
    private lateinit var question: Question
    private val toastTools: ToastTools by inject()
    private val glideTools: GlideTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val handelErrorTools: HandelErrorTools by inject()
    private val answerAdapter: AnswerAdapter by inject()
    private val gson: Gson by inject()
    private val quizViewModel: QuizViewModel by viewModel()
    private val questionList: ArrayList<Question> = ArrayList()
    private val resultQuestionList: ArrayList<Question> = ArrayList()
    private val resultAnswerListId: ArrayList<Int> = ArrayList()

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

    override fun onResume() {
        super.onResume()
        initResumeVideo()
    }

    override fun onPause() {
        super.onPause()
        initPauseVideo()

    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMPQuestion()
        releaseMPAnswer()
    }

    private fun initAction() {
        initAdapter()
        initOnClick()
        initRequestQuiz()
        initOnBackPress()
    }

    private fun initOnClick() {
        binding.btnNextQuestion.setOnClickListener { initCheckQuestion() }
        binding.lnQuestionSoundLayout.imgQuestionPlaySound.setOnClickListener { initPlaySoundQuestion() }
        binding.lnQuestionSoundLayout.imgQuestionPauseSound.setOnClickListener { initPauseSoundQuestion() }
        binding.lnAnswerSoundLayout.imgAnswerPlaySound.setOnClickListener { initPlaySoundAnswer() }
        binding.lnAnswerSoundLayout.imgAnswerPauseSound.setOnClickListener { initPauseSoundAnswer() }
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
        when (it) {
            is ResponseQuizDetailModel -> initQuizDetailModel(it)
            is Throwable -> initThrowable(it)
        }
    }

    private fun initQuizDetailModel(it: ResponseQuizDetailModel) = when (it.details.type) {
        MULTIMEDIA -> initMultimediaQuiz(it)
        else -> initNotMultimedia()
    }

    private fun initNotMultimedia() {
        toastTools.toast(resources.getString(R.string.not_multimedia))
        initOnBackPressed()
    }

    private fun initMultimediaQuiz(it: ResponseQuizDetailModel) {
        initHideLoading()
        questionList.addAll(it.questions)
        initHowDisplayCorrectAnswer(it)
        initQuestion()
    }

    private fun initHowDisplayCorrectAnswer(it: ResponseQuizDetailModel) {
        HOW_DISPLAY_CORRECT_ANSWER = it.details.howDisplayCorrectAnswer
        PASS_CONDITION = it.details.passCondition
    }

    private fun initCheckQuestion() {
        resultQuestionList.add(question)
        questionList.let {
            when {
                it.isEmpty() -> initResult()
                else -> initQuestion()
            }
        }
    }

    private fun initResult() {
        initCancelTimer()
        initPauseVideo()
        releaseMPQuestion()
        releaseMPAnswer()
        initResultFragment()
    }

    private fun initResultFragment() {
        val executor = Executors.newSingleThreadScheduledExecutor()
        executor.schedule({
            activity?.runOnUiThread {
                NavHostFragment.findNavController(this)
                    .navigate(R.id.resultQuizFragment, initBundle())
            }
        }, 1, TimeUnit.MILLISECONDS)
    }

    private fun initBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(BUNDLE_USER_QUESTION_LIST, gson.toJson(resultQuestionList))
        bundle.putString(BUNDLE_USER_ANSWER_LIST_ID, gson.toJson(resultAnswerListId))
        return bundle
    }

    private fun initQuestion() = when {
        questionList.isEmpty() -> initEmptyList()
        else -> initResultList()
    }

    private fun initResultList() {
        question = questionList.first()
        questionList.removeFirst()
        initCancelTimer()
        initStopVideo()
        initHideAllAnswer()
        initStateQuestionFormat()
        checkPersianCharacter(question.title, binding.lnQuestionTextLayout.txtQuestion)
        initHideAnswerQuestion()
        answerAdapter.updateList(question.answers)
    }

    private fun initEmptyList() {
        toastTools.toast(resources.getString(R.string.is_empty_list))
        initOnBackPressed()
    }

    private fun initHideAnswerQuestion() {
        binding.btnNextQuestion.visibility = View.GONE
        binding.lnAnswerSoundLayout.lnAnswerSound.visibility = View.GONE
        binding.lnAnswerVideoLayout.cvAnswerVideo.visibility = View.GONE
        binding.lnAnswerTextLayout.lnAnswerText.visibility = View.GONE
        binding.lnAnswerImageLayout.lnAnswerImage.visibility = View.GONE
    }

    private fun initStateQuestionFormat() {
        when (question.quizDescription.format) {
            FORMAT_TEXT -> initQuestionFormatText(question)
            FORMAT_VIDEO -> initQuestionFormatVideo(question)
            FORMAT_AUDIO -> initQuestionFormatAudio(question)
            else -> initQuestionFormatImage(question)
        }
    }


    private fun initQuestionFormatImage(question: Question) {
        initShowQuestionFormatImage()
        initPeriodImageTime(question)
        glideTools.displayImageOriginal(
            binding.lnQuestionImageLayout.imgQuestion,
            question.quizDescription.urlContent
        )
    }

    private fun initQuestionFormatAudio(question: Question) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        initShowQuestionFormatSound()
        initPeriodSoundTime(question)
        initSoundQuestion(question.quizDescription.urlContent)
    }

    private fun initSoundQuestion(content: String) {
        val proxy: HttpProxyCacheServer = getProxy(requireContext())
        val proxyUrl = proxy.getProxyUrl(content)
        releaseMPQuestion()
        mediaPlayerQuestion = MediaPlayer()
        try {
            mediaPlayerQuestion.setDataSource(requireContext(), Uri.parse(proxyUrl))
            mediaPlayerQuestion.prepare()
            mediaPlayerQuestion.prepareAsync()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
        initPlaySoundQuestion()
    }

    private fun releaseMPQuestion() {
        if (::mediaPlayerQuestion.isInitialized) try {
            mediaPlayerQuestion.release()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun releaseMPAnswer() {
        if (::mediaPlayerAnswer.isInitialized) try {
            mediaPlayerAnswer.release()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun initPlaySoundQuestion() {
        binding.lnQuestionSoundLayout.imgQuestionPlaySound.visibility = View.GONE
        binding.lnQuestionSoundLayout.imgQuestionPauseSound.visibility = View.VISIBLE
        mediaPlayerQuestion.start()
    }

    private fun initPauseSoundQuestion() {
        binding.lnQuestionSoundLayout.imgQuestionPlaySound.visibility = View.VISIBLE
        binding.lnQuestionSoundLayout.imgQuestionPauseSound.visibility = View.GONE
        mediaPlayerQuestion.pause()
    }

    private fun initPlaySoundAnswer() {
        binding.lnAnswerSoundLayout.imgAnswerPlaySound.visibility = View.VISIBLE
        binding.lnAnswerSoundLayout.imgAnswerPauseSound.visibility = View.GONE
        mediaPlayerAnswer.start()
    }

    private fun initPauseSoundAnswer() {
        binding.lnAnswerSoundLayout.imgAnswerPlaySound.visibility = View.GONE
        binding.lnAnswerSoundLayout.imgAnswerPauseSound.visibility = View.VISIBLE
        mediaPlayerAnswer.pause()
    }

    private fun initQuestionFormatVideo(question: Question) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        initShowQuestionFormatVideo()
        initPeriodVideoTime(question)
        initVideoQuestion(question.quizDescription.urlContent)
    }

    private fun initVideoQuestion(content: String) {
        val proxy: HttpProxyCacheServer = getProxy(requireContext())
        val proxyUrl = proxy.getProxyUrl(content)
        binding.lnQuestionVideoLayout.viewVideoQuestion.setVideoPath(proxyUrl)
        val mediaController = MediaController(requireContext())
        binding.lnQuestionVideoLayout.viewVideoQuestion.setMediaController(mediaController)
        mediaController.setAnchorView(binding.lnQuestionVideoLayout.viewVideoQuestion)
        binding.lnQuestionVideoLayout.viewVideoQuestion.start()
    }

    private fun initQuestionFormatText(question: Question) {
        initShowQuestionFormatText()
        initPeriodTextTime(question)
        binding.lnQuestionTextLayout.txtQuestion.text = question.title
    }

    private fun initShowQuestionFormatText() {
        binding.lnQuestionVideoLayout.cvQuestionVideo.visibility = View.GONE
        binding.lnQuestionTextLayout.lnText.visibility = View.VISIBLE
        binding.lnQuestionImageLayout.lnImage.visibility = View.GONE
        binding.lnQuestionSoundLayout.lnSound.visibility = View.GONE
    }

    private fun initShowQuestionFormatImage() {
        binding.lnQuestionVideoLayout.cvQuestionVideo.visibility = View.GONE
        binding.lnQuestionImageLayout.lnImage.visibility = View.VISIBLE
        binding.lnQuestionSoundLayout.lnSound.visibility = View.GONE
        binding.lnQuestionTextLayout.lnText.visibility = View.GONE
    }

    private fun initShowQuestionFormatSound() {
        binding.lnQuestionVideoLayout.cvQuestionVideo.visibility = View.GONE
        binding.lnQuestionSoundLayout.lnSound.visibility = View.VISIBLE
        binding.lnQuestionImageLayout.lnImage.visibility = View.GONE
        binding.lnQuestionTextLayout.lnText.visibility = View.GONE
    }

    private fun initShowQuestionFormatVideo() {
        binding.lnQuestionVideoLayout.cvQuestionVideo.visibility = View.VISIBLE
        binding.lnQuestionSoundLayout.lnSound.visibility = View.GONE
        binding.lnQuestionImageLayout.lnImage.visibility = View.GONE
        binding.lnQuestionTextLayout.lnText.visibility = View.GONE
    }

    private fun initPeriodTextTime(question: Question) {
        binding.lnQuestionTextLayout.progressPeriodTextTime.max = question.periodTime
        binding.lnQuestionTextLayout.progressPeriodTextTime.progress = question.periodTime
        timer = object : CountDownTimer((question.periodTime * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) =
                initTick(millisUntilFinished, binding.lnQuestionTextLayout.progressPeriodTextTime)

            override fun onFinish() = initFinish()
        }
        timer.start()
    }

    private fun initPeriodImageTime(question: Question) {
        binding.lnQuestionImageLayout.progressPeriodImageTime.max = question.periodTime
        binding.lnQuestionImageLayout.progressPeriodImageTime.progress = question.periodTime
        timer = object : CountDownTimer((question.periodTime * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) = initTick(
                millisUntilFinished,
                binding.lnQuestionImageLayout.progressPeriodImageTime
            )

            override fun onFinish() = initFinish()
        }
        timer.start()
    }

    private fun initPeriodVideoTime(question: Question) {
        binding.lnQuestionVideoLayout.progressPeriodVideoTime.max = question.periodTime
        binding.lnQuestionVideoLayout.progressPeriodVideoTime.progress = question.periodTime
        timer = object : CountDownTimer((question.periodTime * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) = initTick(
                millisUntilFinished,
                binding.lnQuestionVideoLayout.progressPeriodVideoTime
            )

            override fun onFinish() = initFinish()
        }
        timer.start()
    }

    private fun initPeriodSoundTime(question: Question) {
        binding.lnQuestionSoundLayout.progressPeriodSoundTime.max = question.periodTime
        binding.lnQuestionSoundLayout.progressPeriodSoundTime.progress = question.periodTime
        timer = object : CountDownTimer((question.periodTime * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) = initTick(
                millisUntilFinished,
                binding.lnQuestionSoundLayout.progressPeriodSoundTime
            )

            override fun onFinish() = initFinish()
        }
        timer.start()
    }

    private fun initFinish() = iniResultAnswerFinish(answerAdapter.initFindIsCorrectAnswer()!!)

    private fun iniResultAnswerFinish(answer: Answer) {
        initSelected(answer)
        when (HOW_DISPLAY_CORRECT_ANSWER) {
            STEP_BY_STEP -> initAnswerStepByStepFinish(answer)
            else -> initAnswerFinalLevel()
        }
    }

    private fun initAnswerStepByStepFinish(it: Answer) {
        answerAdapter.disableClick()
        initStateListFormat(it)
    }

    private fun initAnswerFinalLevel() {
        answerAdapter.disableClick()
        binding.btnNextQuestion.visibility = View.VISIBLE
    }

    private fun initThrowable(it: Throwable) {
        initHideLoading()
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
        val resultAnswer = initSelected(any)
        when (HOW_DISPLAY_CORRECT_ANSWER) {
            STEP_BY_STEP -> initResultStepByStep(resultAnswer)
            else -> initResultFinalLevel(resultAnswer)
        }

    }

    private fun initSelected(any: Any): Answer {
        val answer: Answer = any as Answer
        val index = question.answers.indexOf(answer)
        val resultAnswer = question.answers[index]
        resultAnswer.isSelected = true
        return resultAnswer
    }

    private fun initResultFinalLevel(answer: Answer) {
        initHideStepByStep()
        initFinalLevel(answer)
    }

    private fun initHideStepByStep() {
        binding.progressStepByStep.visibility = View.GONE
    }

    private fun initResultStepByStep(answer: Answer) {
        initShowStepByStep()
        initStepByStep(answer)
    }

    private fun initShowStepByStep() {
        binding.progressStepByStep.visibility = View.VISIBLE
        binding.progressStepByStep.max = PASS_CONDITION
    }

    private fun initStepByStep(answer: Answer) = when (answer.isCorrect) {
        1 -> initSuccessAnswerStepByStep(answer)
        else -> initUnSuccessAnswerStepByStep(answer)
    }

    private fun initSuccessAnswerStepByStep(answer: Answer) {
        answerAdapter.answerSuccessQuestion(answer)
        initStateListFormat(answer)
        answer.isSuccess = 1
        resultAnswerListId.add(answer.id)
        initStopVideo()
        initPassCondition()
    }

    private fun initPassCondition() {
        var currentProgress = binding.progressStepByStep.progress
        currentProgress += 1
        binding.progressStepByStep.progress = currentProgress
        when (PASS_CONDITION) {
            currentProgress -> initResult()
        }
    }

    private fun initUnSuccessAnswerStepByStep(answer: Answer) {
        val isCorrectAnswer = answerAdapter.initFindIsCorrectAnswer()
        answerAdapter.answerUnSuccessQuestion(answer)
        initStateListFormat(isCorrectAnswer!!)
        answer.isSuccess = 2
        resultAnswerListId.add(answer.id)
    }

    private fun initFinalLevel(answer: Answer) = when (answer.isCorrect) {
        1 -> initSuccessAnswerFinalLevel(answer)
        else -> initUnSuccessAnswerFinalLevel(answer)
    }

    private fun initSuccessAnswerFinalLevel(answer: Answer) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        answer.isSuccess = 1
        resultAnswerListId.add(answer.id)
        answerAdapter.answerCheckLevel(answer)
        initCancelTimer()
    }

    private fun initUnSuccessAnswerFinalLevel(answer: Answer) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        answer.isSuccess = 2
        resultAnswerListId.add(answer.id)
        answerAdapter.answerCheckLevel(answer)
        initCancelTimer()
    }

    private fun initStateListFormat(answer: Answer) = when (answer.description.format) {
        FORMAT_TEXT -> initListFormatText(answer)
        FORMAT_VIDEO -> initListFormatVideo(answer)
        FORMAT_AUDIO -> initListFormatSound(answer)
        else -> initListFormatImage(answer)
    }

    private fun initHideAllAnswer() {
        binding.lnAnswerImageLayout.lnAnswerImage.visibility = View.GONE
        binding.lnAnswerVideoLayout.cvAnswerVideo.visibility = View.GONE
        binding.lnAnswerSoundLayout.lnAnswerSound.visibility = View.GONE
        binding.lnAnswerTextLayout.lnAnswerText.visibility = View.GONE
    }

    private fun initShowAnswerFormatText() {
        binding.lnAnswerImageLayout.imgAnswer.visibility = View.GONE
        binding.lnAnswerVideoLayout.cvAnswerVideo.visibility = View.GONE
        binding.lnAnswerTextLayout.lnAnswerText.visibility = View.VISIBLE
        binding.lnAnswerSoundLayout.lnAnswerSound.visibility = View.GONE
    }

    private fun initShowAnswerFormatImage() {
        binding.lnAnswerImageLayout.lnAnswerImage.visibility = View.VISIBLE
        binding.lnAnswerVideoLayout.cvAnswerVideo.visibility = View.GONE
        binding.lnAnswerSoundLayout.lnAnswerSound.visibility = View.GONE
        binding.lnAnswerTextLayout.lnAnswerText.visibility = View.GONE
    }

    private fun initShowAnswerFormatSound() {
        binding.lnAnswerImageLayout.lnAnswerImage.visibility = View.GONE
        binding.lnAnswerVideoLayout.cvAnswerVideo.visibility = View.GONE
        binding.lnAnswerSoundLayout.lnAnswerSound.visibility = View.VISIBLE
        binding.lnAnswerTextLayout.lnAnswerText.visibility = View.GONE
    }

    private fun initShowAnswerFormatVideo() {
        binding.lnAnswerImageLayout.lnAnswerImage.visibility = View.GONE
        binding.lnAnswerVideoLayout.cvAnswerVideo.visibility = View.VISIBLE
        binding.lnAnswerSoundLayout.lnAnswerSound.visibility = View.GONE
        binding.lnAnswerTextLayout.lnAnswerText.visibility = View.GONE
    }

    private fun initListFormatText(answer: Answer) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        initConvertHtml(answer)
        initShowAnswerFormatText()
        initCancelTimer()
        initStopQuestionVideo()
    }

    private fun initConvertHtml(answer: Answer) {
        answer.description.content.let {
            if (!it.isNullOrEmpty()) {
                val plainText = Html.fromHtml(it).toString()
                binding.lnAnswerTextLayout.txtTextAnswer.text = plainText
            }
        }

    }

    private fun initListFormatVideo(answer: Answer) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        initShowAnswerFormatVideo()
        initStopVideoQuestion()
        initVideoView(answer.description.urlContent)
        initCancelTimer()
        initStopQuestionVideo()
    }

    private fun initVideoView(content: String) {
        val proxy: HttpProxyCacheServer = getProxy(requireContext())
        val proxyUrl = proxy.getProxyUrl(content)
        binding.lnAnswerVideoLayout.viewVideo.setVideoPath(proxyUrl)
        val mediaController = MediaController(requireContext())
        binding.lnAnswerVideoLayout.viewVideo.setMediaController(mediaController)
        mediaController.setAnchorView(binding.lnAnswerVideoLayout.viewVideo)
        binding.lnAnswerVideoLayout.viewVideo.start()
    }

    private fun initStopVideoQuestion() {
        if (binding.lnQuestionVideoLayout.viewVideoQuestion.isPlaying)
            binding.lnQuestionVideoLayout.viewVideoQuestion.pause()
    }

    private fun initStopVideo() {
        if (binding.lnAnswerVideoLayout.viewVideo.isPlaying)
            binding.lnAnswerVideoLayout.viewVideo.pause()
        if (binding.lnQuestionVideoLayout.viewVideoQuestion.isPlaying)
            binding.lnQuestionVideoLayout.viewVideoQuestion.pause()
    }

    private fun initStopQuestionVideo() {
        if (binding.lnQuestionVideoLayout.viewVideoQuestion.isPlaying)
            binding.lnQuestionVideoLayout.viewVideoQuestion.pause()
    }

    private fun initPauseVideo() {
        if (binding.lnAnswerVideoLayout.viewVideo.isPlaying)
            binding.lnAnswerVideoLayout.viewVideo.pause()
        if (binding.lnQuestionVideoLayout.viewVideoQuestion.isPlaying)
            binding.lnQuestionVideoLayout.viewVideoQuestion.pause()
    }

    private fun initResumeVideo() {
        binding.lnAnswerVideoLayout.viewVideo.resume()
    }

    private fun initListFormatSound(answer: Answer) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        initShowAnswerFormatSound()
        initSoundAnswer(answer.description.urlContent)
        initCancelTimer()
        initStopQuestionVideo()
    }

    private fun initListFormatImage(answer: Answer) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        initShowAnswerFormatImage()
        initCancelTimer()
        initStopQuestionVideo()
        glideTools.displayImageOriginal(
            binding.lnAnswerImageLayout.imgAnswer,
            answer.description.urlContent
        )
    }

    private fun initSoundAnswer(content: String) {
        val proxy: HttpProxyCacheServer = getProxy(requireContext())
        val proxyUrl = proxy.getProxyUrl(content)
        releaseMPAnswer()
        try {
            mediaPlayerAnswer = MediaPlayer()
            mediaPlayerAnswer.setDataSource(requireContext(), Uri.parse(proxyUrl))
            mediaPlayerQuestion.prepare()
            mediaPlayerQuestion.prepareAsync()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
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
        initPauseVideo()
        releaseMPQuestion()
        releaseMPAnswer()
        AppQuiz.activity.finish()
    }

    private fun initCancelTimer() {
        if (this::timer.isInitialized)
            timer.cancel()
    }
}