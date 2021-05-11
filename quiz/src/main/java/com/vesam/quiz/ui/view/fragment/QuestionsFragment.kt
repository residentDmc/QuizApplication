package com.vesam.quiz.ui.view.fragment

import android.app.Activity.RESULT_OK
import android.content.Context.VIBRATOR_SERVICE
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.*
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_detail.Answer
import com.vesam.quiz.data.model.quiz_detail.Question
import com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel
import com.vesam.quiz.databinding.FragmentQuestionsBinding
import com.vesam.quiz.interfaces.OnClickListener
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.ui.view.activity.FullScreenActivity
import com.vesam.quiz.ui.view.adapter.answer_quiz_list.AnswerAdapter
import com.vesam.quiz.ui.viewmodel.QuizViewModel
import com.vesam.quiz.utils.application.AppQuiz
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_CURRENT_POSITION
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_PATH
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_USER_ANSWER_LIST_ID
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_USER_QUESTION_LIST
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.CORRECT_ANSWER
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.EXAM_PASS
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_AUDIO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_TEXT
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_VIDEO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.HOW_DISPLAY_CORRECT_ANSWER
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MULTIMEDIA
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.PASS_CONDITION
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.REQUEST_CODE_FULL_SCREEN
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.STEP_BY_STEP
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_API_TOKEN_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_QUIZ_ID_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_UUID_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.WRONG_ANSWER
import com.vesam.quiz.utils.extention.initFindFileInStorage
import com.vesam.quiz.utils.music_manager.BeatBox
import com.vesam.quiz.utils.option.Option
import com.vesam.quiz.utils.tools.GlideTools
import com.vesam.quiz.utils.tools.HandelErrorTools
import com.vesam.quiz.utils.tools.ThrowableTools
import com.vesam.quiz.utils.tools.ToastTools
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class QuestionsFragment : Fragment() {
    private lateinit var binding: FragmentQuestionsBinding
    private lateinit var mediaPlayerQuestion: MediaPlayer
    private lateinit var disposableObserver: Disposable
    private lateinit var mediaPlayerAnswer: MediaPlayer
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
        initResumeSound()
    }

    private fun initResumeSound() {
        initResumeSoundQuestion()
        initResumeSoundAnswer()
    }

    private fun initResumeSoundQuestion() {
        binding.lnQuestionSoundLayout.imgQuestionPlaySound.visibility = View.GONE
        binding.lnQuestionSoundLayout.imgQuestionPauseSound.visibility = View.VISIBLE
    }

    private fun initResumeSoundAnswer() {
        binding.lnAnswerSoundLayout.imgAnswerPlaySound.visibility = View.VISIBLE
        binding.lnAnswerSoundLayout.imgAnswerPauseSound.visibility = View.GONE
    }

    private fun initResumeVideo() = try {
        when (binding.lnAnswerVideoLayout.cvAnswerVideo.visibility) {
            View.VISIBLE -> initShowAnswerVideoResumed()
            else -> initShowQuestionVideoResumed()
        }
    } catch (e: Exception) {
        handelErrorTools.handelError(e)
    }

    private fun initShowAnswerVideoResumed() {
        if (binding.lnAnswerVideoLayout.videoView.isPlaying)
            binding.lnAnswerVideoLayout.videoView.resume()
    }

    private fun initShowQuestionVideoResumed() {
        if (binding.lnQuestionVideoLayout.viewVideoQuestion.isPlaying)
            binding.lnQuestionVideoLayout.viewVideoQuestion.resume()
    }

    override fun onPause() {
        super.onPause()
        initPauseVideo()
        initPauseSoundAnswer()
        initPauseSoundQuestion()
    }

    override fun onDestroy() {
        super.onDestroy()
        initDestroyVideo()
        initStateAudio()
    }

    private fun initDestroyVideo() = try {
        when (binding.lnAnswerVideoLayout.cvAnswerVideo.visibility) {
            View.VISIBLE -> initShowAnswerVideoDestroy()
            else -> initShowQuestionVideoDestroy()
        }
    } catch (e: Exception) {
        handelErrorTools.handelError(e)
    }

    private fun initShowQuestionVideoDestroy() {
        if (binding.lnQuestionVideoLayout.viewVideoQuestion.isPlaying)
            binding.lnQuestionVideoLayout.viewVideoQuestion.stopPlayback()
    }

    private fun initShowAnswerVideoDestroy() {
        if (binding.lnAnswerVideoLayout.videoView.isPlaying)
            binding.lnAnswerVideoLayout.videoView.stopPlayback()
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
        binding.lnQuestionImageLayout.imgQuestion.setOnClickListener { initQuestionImage() }
        binding.lnAnswerImageLayout.imgAnswer.setOnClickListener { initAnswerImage() }
        binding.lnAnswerVideoLayout.imgFullScreen.setOnClickListener { initFullScreenVideo() }
        binding.lnQuestionVideoLayout.imgFullScreen.setOnClickListener { initFullScreenVideo() }
    }

    private fun initFullScreenVideo() {
        val intent = Intent()
        intent.putExtra(BUNDLE_CURRENT_POSITION, initCurrentPosition())
        intent.putExtra(BUNDLE_PATH, initPath())
        intent.setClass(requireActivity(), FullScreenActivity::class.java)
        requireActivity().startActivityForResult(intent, REQUEST_CODE_FULL_SCREEN)

    }

    private fun initCurrentPosition(): Int {
        val currentPosition: Int
        when (binding.lnAnswerVideoLayout.cvAnswerVideo.visibility) {
            View.VISIBLE -> {
                currentPosition = binding.lnAnswerVideoLayout.videoView.currentPosition
                binding.lnAnswerVideoLayout.videoView.pause()
            }
            else -> {
                currentPosition = binding.lnQuestionVideoLayout.viewVideoQuestion.currentPosition
                binding.lnQuestionVideoLayout.viewVideoQuestion.pause()
            }
        }
        return currentPosition
    }

    private fun initPath(): String = when (binding.lnAnswerVideoLayout.cvAnswerVideo.visibility) {
        View.VISIBLE -> binding.lnAnswerVideoLayout.videoView.tag as String
        else -> binding.lnQuestionVideoLayout.viewVideoQuestion.tag as String
    }

    private fun initQuestionImage() {
        val urlContent: String = binding.lnQuestionImageLayout.imgQuestion.tag as String
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentFullscreenSlider = FragmentFullscreenSliderImageQuestion()
        val transaction = fragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.add(android.R.id.content, fragmentFullscreenSlider).addToBackStack(null)
            .commit()
        fragmentFullscreenSlider.setImage(urlContent)
    }

    private fun initAnswerImage() {
        val urlContent: String = binding.lnAnswerImageLayout.imgAnswer.tag as String
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentFullscreenSlider = FragmentFullscreenSliderImageQuestion()
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
        initPauseVideo()
        initStateAudio()
        initResultFragment()
    }

    private fun initStateAudio() = when (binding.lnAnswerSoundLayout.lnAnswerSound.visibility) {
        View.VISIBLE -> releaseMPAnswer()
        else -> releaseMPQuestion()
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
        initStopAudio()
        initStopVideo()
        initHideAllAnswer()
        initStateQuestionFormat()
        initHideAnswerQuestion()
        answerAdapter.updateList(question.answers)
    }

    private fun initStopAudio() = when (binding.lnAnswerSoundLayout.lnAnswerSound.visibility) {
        View.VISIBLE -> stopMPAnswer()
        else -> stopMPQuestion()
    }

    private fun stopMPAnswer() {
        binding.lnAnswerSoundLayout.imgAnswerPlaySound.visibility = View.VISIBLE
        binding.lnAnswerSoundLayout.imgAnswerPauseSound.visibility = View.GONE
        if (::mediaPlayerAnswer.isInitialized) try {
            mediaPlayerAnswer.stop()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun stopMPQuestion() {
        binding.lnQuestionSoundLayout.imgQuestionPlaySound.visibility = View.VISIBLE
        binding.lnQuestionSoundLayout.imgQuestionPauseSound.visibility = View.GONE
        if (::mediaPlayerQuestion.isInitialized) try {
            mediaPlayerQuestion.stop()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
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
        when (question.quizDescription!!.format) {
            FORMAT_TEXT -> initQuestionFormatText(question)
            FORMAT_VIDEO -> initQuestionFormatVideo(question)
            FORMAT_AUDIO -> initQuestionFormatAudio(question)
            else -> initQuestionFormatImage(question)
        }
    }


    private fun initQuestionFormatImage(question: Question) {
        initShowQuestionFormatImage()
        initPeriodImageTime(question)
        initSetTag(binding.lnQuestionImageLayout.imgQuestion, question.quizDescription!!.urlContent)
        initTitleImageView(question)
        glideTools.displayImageOriginal(
            binding.lnQuestionImageLayout.imgQuestion,
            question.quizDescription.urlContent
        )
    }

    private fun initTitleText(question: Question) = when {
        question.title.isEmpty() -> binding.lnQuestionTextLayout.lnTitle.visibility = View.GONE
        else -> {
            binding.lnQuestionTextLayout.lnTitle.visibility = View.VISIBLE
            binding.lnQuestionTextLayout.txtTitleText.text = question.title
            binding.lnQuestionTextLayout.txtTitleText.setMovementMethod(ScrollingMovementMethod.getInstance())
        }
    }

    private fun initTitleImageView(question: Question) = when {
        question.title.isEmpty() -> binding.lnQuestionImageLayout.lnTitle.visibility = View.GONE
        else -> {
            binding.lnQuestionImageLayout.lnTitle.visibility = View.VISIBLE
            binding.lnQuestionImageLayout.txtTitleImage.text = question.title
            binding.lnQuestionImageLayout.txtTitleImage.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }

    private fun initTitleAudio(question: Question) = when {
        question.title.isEmpty() -> binding.lnQuestionSoundLayout.lnTitle.visibility = View.GONE
        else -> {
            binding.lnQuestionSoundLayout.lnTitle.visibility = View.VISIBLE
            binding.lnQuestionSoundLayout.txtTitleImage.text = question.title
            binding.lnQuestionSoundLayout.txtTitleImage.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }

    private fun initTitleVideo(question: Question) = when {
        question.title.isEmpty() -> binding.lnQuestionVideoLayout.lnTitle.visibility = View.GONE
        else -> {
            binding.lnQuestionVideoLayout.lnTitle.visibility = View.VISIBLE
            binding.lnQuestionVideoLayout.txtTitleImage.text = question.title
            binding.lnQuestionVideoLayout.txtTitleImage.setMovementMethod(ScrollingMovementMethod.getInstance());
        }
    }

    private fun initSetTag(view: View, urlContent: String) {
        view.tag = urlContent
    }

    private fun initQuestionFormatAudio(question: Question) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        initTitleAudio(question)
        initShowQuestionFormatSound()
        initPeriodSoundTime(question)
        initSoundQuestion(question.uriPath)
    }

    private fun initSoundQuestion(content: String) {
        releaseMPQuestion()
        mediaPlayerQuestion = MediaPlayer()
        try {
            mediaPlayerQuestion.setDataSource(
                requireContext(), Uri.parse(
                    initFindFileInStorage(
                        content
                    )
                )
            )
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
        initPauseSoundAnswer()
        initPauseAnswerVideo()
        binding.lnQuestionSoundLayout.imgQuestionPlaySound.visibility = View.GONE
        binding.lnQuestionSoundLayout.imgQuestionPauseSound.visibility = View.VISIBLE
        if (::mediaPlayerQuestion.isInitialized)
            mediaPlayerQuestion.start()
    }

    private fun initPauseSoundQuestion() {
        binding.lnQuestionSoundLayout.imgQuestionPlaySound.visibility = View.VISIBLE
        binding.lnQuestionSoundLayout.imgQuestionPauseSound.visibility = View.GONE
        if (::mediaPlayerQuestion.isInitialized) {
            try {
                if (mediaPlayerQuestion.isPlaying) {
                    mediaPlayerQuestion.pause()
                }
            } catch (e: Exception) {
                handelErrorTools.handelError(e)
            }
        }
    }

    private fun initPlaySoundAnswer() {
        initPauseSoundQuestion()
        initPauseQuestionVideo()
        binding.lnAnswerSoundLayout.imgAnswerPlaySound.visibility = View.GONE
        binding.lnAnswerSoundLayout.imgAnswerPauseSound.visibility = View.VISIBLE
        if (::mediaPlayerAnswer.isInitialized)
            mediaPlayerAnswer.start()
    }

    private fun initPauseSoundAnswer() {
        binding.lnAnswerSoundLayout.imgAnswerPlaySound.visibility = View.VISIBLE
        binding.lnAnswerSoundLayout.imgAnswerPauseSound.visibility = View.GONE
        if (::mediaPlayerAnswer.isInitialized) {
            try {
                if (mediaPlayerAnswer.isPlaying)
                    mediaPlayerAnswer.pause()
            } catch (e: Exception) {
                handelErrorTools.handelError(e)
            }
        }
    }

    private fun initQuestionFormatVideo(question: Question) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        initTitleVideo(question)
        initShowQuestionFormatVideo()
        initPeriodVideoTime(question)
        initVideoQuestion(question.uriPath)
    }

    private fun initVideoQuestion(content: String) {
        val uri = initFindFileInStorage(content)
        binding.lnQuestionVideoLayout.viewVideoQuestion.tag = uri
        binding.lnQuestionVideoLayout.viewVideoQuestion.setVideoPath(uri)
        val mediaController = MediaController(requireActivity())
        binding.lnQuestionVideoLayout.viewVideoQuestion.setMediaController(mediaController)
        mediaController.setAnchorView(binding.lnQuestionVideoLayout.viewVideoQuestion)
        binding.lnQuestionVideoLayout.viewVideoQuestion.start()
    }

    private fun initQuestionFormatText(question: Question) {
        initTitleText(question)
        initShowQuestionFormatText()
        initConvertHtmlQuestion(question)
        initPeriodTextTime(question)
    }

    private fun initConvertHtmlQuestion(question: Question) {
        question.quizDescription!!.content.let {
            if (!it.isNullOrEmpty()) {
                val plainText = Html.fromHtml(it).toString()
                binding.lnQuestionTextLayout.txtQuestion.text = plainText
            }
        }
    }

    private fun initAutomaticAnimationScroll() = Thread {
        Handler(Looper.getMainLooper()).postDelayed({
            initScrollDown()
        }, 1000)
    }.start()

    private fun initScrollDown() {
        val scrollTo: Int =
            (binding.childNestedScrollView.parent.parent as View).bottom + binding.childNestedScrollView.bottom
        binding.nestedScrollView.smoothScrollTo(0, scrollTo)
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
        timeDownProgressBar(binding.lnQuestionTextLayout.progressPeriodTextTime,
            question.periodTime,
            object : OnClickListener {
                override fun onClickListener() = initFinish()
            })
    }

    private fun initPeriodImageTime(question: Question) {
        timeDownProgressBar(binding.lnQuestionImageLayout.progressPeriodImageTime,
            question.periodTime,
            object : OnClickListener {
                override fun onClickListener() = initFinish()
            })
    }

    private fun initPeriodVideoTime(question: Question) {
        timeDownProgressBar(binding.lnQuestionVideoLayout.progressPeriodVideoTime,
            question.periodTime,
            object : OnClickListener {
                override fun onClickListener() = initFinish()
            })
    }

    private fun initPeriodSoundTime(question: Question) {
        timeDownProgressBar(binding.lnQuestionSoundLayout.progressPeriodSoundTime,
            question.periodTime,
            object : OnClickListener {
                override fun onClickListener() = initFinish()
            })
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
        initDispose()
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
        initPlayCorrectAnswer()
        initStateListFormat(answer)
        answer.isSuccess = 1
        resultAnswerListId.add(answer.id)
        initPassCondition()
    }

    private fun initPlayCorrectAnswer() {
        val beatBox = BeatBox(requireContext(), handelErrorTools)
        val mediaPlayer = MediaPlayer()
        for (item in beatBox.soundsList) {
            if (item.soundName == CORRECT_ANSWER) {
                beatBox.play(
                    item,
                    mediaPlayer,
                    false
                )
            }
        }
        initVibration()
    }

    private fun initPlayExamPass() {
        val beatBox = BeatBox(requireContext(), handelErrorTools)
        val mediaPlayer = MediaPlayer()
        for (item in beatBox.soundsList) {
            if (item.soundName == EXAM_PASS) {
                beatBox.play(
                    item,
                    mediaPlayer,
                    false
                )
            }
        }
        initVibration()
    }

    private fun initPlayWrongAnswer() {
        val beatBox = BeatBox(requireContext(), handelErrorTools)
        val mediaPlayer = MediaPlayer()
        for (item in beatBox.soundsList) {
            if (item.soundName == WRONG_ANSWER) {
                beatBox.play(
                    item,
                    mediaPlayer,
                    false
                )
            }
        }
        initVibration()
    }

    private fun initVibration() {
        val v = requireActivity().getSystemService(VIBRATOR_SERVICE) as Vibrator?
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> v!!.vibrate(
                VibrationEffect.createOneShot(
                    500,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
            else -> v!!.vibrate(500)
        }
    }

    private fun initPassCondition() {
        var currentProgress = binding.progressStepByStep.progress
        currentProgress += 1
        binding.progressStepByStep.progress = currentProgress
        when (PASS_CONDITION) {
            currentProgress -> initResultCondition()
        }
    }

    private fun initResultCondition() {
        resultQuestionList.add(question)
        initResult()
    }

    private fun initUnSuccessAnswerStepByStep(answer: Answer) {
        val isCorrectAnswer = answerAdapter.initFindIsCorrectAnswer()
        answerAdapter.answerUnSuccessQuestion(answer)
        answerAdapter.answerSuccessQuestion(isCorrectAnswer!!)
        initStateListFormat(isCorrectAnswer)
        initPlayWrongAnswer()
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
    }

    private fun initUnSuccessAnswerFinalLevel(answer: Answer) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        answer.isSuccess = 2
        resultAnswerListId.add(answer.id)
        answerAdapter.answerCheckLevel(answer)
    }

    private fun initStateListFormat(answer: Answer) = when (answer.description!!.format) {
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
        binding.lnAnswerImageLayout.lnAnswerImage.visibility = View.GONE
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
        initConvertHtmlAnswer(answer)
        initShowAnswerFormatText()
        initAutomaticAnimationScroll()
        initPauseQuestionVideo()
        initPauseSoundQuestion()
    }

    private fun initConvertHtmlAnswer(answer: Answer) {
        answer.description!!.content.let {
            if (!it.isNullOrEmpty()) {
                val plainText = Html.fromHtml(it).toString()
                binding.lnAnswerTextLayout.txtTextAnswer.text = plainText
            }
        }
    }

    private fun initListFormatVideo(answer: Answer) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        initShowAnswerFormatVideo()
        initVideoView(answer.uriPath)
        initPauseQuestionVideo()
        initAutomaticAnimationScroll()
        initPauseSoundQuestion()
    }

    private fun initVideoView(content: String) {
        val uri = initFindFileInStorage(content)
        binding.lnAnswerVideoLayout.videoView.tag = uri
        binding.lnAnswerVideoLayout.videoView.setVideoPath(uri)
        val mediaController = MediaController(requireActivity())
        binding.lnAnswerVideoLayout.videoView.setMediaController(mediaController)
        mediaController.setAnchorView(binding.lnAnswerVideoLayout.videoView)
        binding.lnAnswerVideoLayout.videoView.start()
    }

    private fun initStopVideo() = try {
        when (binding.lnAnswerVideoLayout.cvAnswerVideo.visibility) {
            View.VISIBLE -> initShowAnswerVideoStop()
            else -> initShowQuestionVideoStop()
        }
    } catch (e: Exception) {
        handelErrorTools.handelError(e)
    }

    private fun initShowQuestionVideoStop() {
        if (binding.lnQuestionVideoLayout.viewVideoQuestion.isPlaying) {
            binding.lnQuestionVideoLayout.viewVideoQuestion.stopPlayback()
            binding.lnQuestionVideoLayout.viewVideoQuestion.setMediaController(null)
        }
    }

    private fun initShowAnswerVideoStop() {
        if (binding.lnAnswerVideoLayout.videoView.isPlaying) {
            binding.lnAnswerVideoLayout.videoView.stopPlayback()
            binding.lnAnswerVideoLayout.videoView.setMediaController(null)
        }
    }

    private fun initPauseQuestionVideo() {
        try {
            if (binding.lnQuestionVideoLayout.viewVideoQuestion.isPlaying)
                binding.lnQuestionVideoLayout.viewVideoQuestion.pause()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun initPauseAnswerVideo() {
        try {
            if (binding.lnAnswerVideoLayout.videoView.isPlaying)
                binding.lnAnswerVideoLayout.videoView.pause()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun initPauseVideo() = try {
        when (binding.lnAnswerVideoLayout.cvAnswerVideo.visibility) {
            View.VISIBLE -> initShowAnswerVideoPause()
            else -> initShowQuestionVideoPause()
        }
    } catch (e: Exception) {
        handelErrorTools.handelError(e)
    }

    private fun initShowQuestionVideoPause() {
        if (binding.lnQuestionVideoLayout.viewVideoQuestion.isPlaying)
            binding.lnQuestionVideoLayout.viewVideoQuestion.pause()
    }

    private fun initShowAnswerVideoPause() {
        if (binding.lnAnswerVideoLayout.videoView.isPlaying)
            binding.lnAnswerVideoLayout.videoView.pause()
    }

    private fun initListFormatSound(answer: Answer) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        initShowAnswerFormatSound()
        initPauseQuestionVideo()
        initPauseSoundQuestion()
        initAutomaticAnimationScroll()
        initSoundAnswer(answer.uriPath)
    }

    private fun initListFormatImage(answer: Answer) {
        binding.btnNextQuestion.visibility = View.VISIBLE
        initShowAnswerFormatImage()
        initPauseQuestionVideo()
        initPauseSoundQuestion()
        initAutomaticAnimationScroll()
        initSetTag(binding.lnAnswerImageLayout.imgAnswer, answer.description!!.urlContent)
        glideTools.displayImageOriginal(
            binding.lnAnswerImageLayout.imgAnswer,
            answer.description.urlContent
        )
    }

    private fun initSoundAnswer(content: String) {
        releaseMPAnswer()
        mediaPlayerAnswer = MediaPlayer()
        try {
            mediaPlayerAnswer.setDataSource(
                requireContext(), Uri.parse(
                    initFindFileInStorage(
                        content
                    )
                )
            )
            mediaPlayerAnswer.prepare()
            mediaPlayerAnswer.prepareAsync()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun timeDownProgressBar(
        progressBar: ProgressBar,
        time: Int,
        onClickListener: OnClickListener
    ) {
        var differentTime = time.toLong() * 8
        progressBar.max = differentTime.toInt()
        progressBar.progress = differentTime.toInt()
        disposableObserver =
            Observable.intervalRange(0, differentTime, 0, 50, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(onClickListener::onClickListener)
                .subscribe {
                    differentTime--
                    progressBar.progress = (differentTime).toInt()
                }
    }

    private fun initDispose() {
        if (::disposableObserver.isInitialized)
            disposableObserver.dispose()
    }

    private fun initOnBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = initOnBackPressed()
            })
    }

    private fun initOnBackPressed() {
        initPauseVideo()
        initStateAudio()
        initExit()
    }

    private fun initExit() {
        AppQuiz.activity.finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_FULL_SCREEN) initResultIntent(
            data
        )
    }

    private fun initResultIntent(data: Intent?) {
        val currentPosition = data!!.extras!!.getInt(BUNDLE_CURRENT_POSITION, 0)
        when (binding.lnAnswerVideoLayout.cvAnswerVideo.visibility) {
            View.VISIBLE -> initResumeAnswerVideo(currentPosition)
            else -> initResumeQuestionVideo(currentPosition)
        }
    }

    private fun initResumeAnswerVideo(currentPosition: Int) {
        binding.lnAnswerVideoLayout.videoView.seekTo(currentPosition)
        binding.lnAnswerVideoLayout.videoView.start()
    }

    private fun initResumeQuestionVideo(currentPosition: Int) {
        binding.lnQuestionVideoLayout.viewVideoQuestion.seekTo(currentPosition)
        binding.lnQuestionVideoLayout.viewVideoQuestion.start()
    }
}