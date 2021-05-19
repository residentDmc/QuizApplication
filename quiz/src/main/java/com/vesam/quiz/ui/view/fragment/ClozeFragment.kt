package com.vesam.quiz.ui.view.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.MediaController
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_detail.Answer
import com.vesam.quiz.data.model.quiz_detail.Description
import com.vesam.quiz.data.model.quiz_detail.Details
import com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel
import com.vesam.quiz.databinding.FragmentClozeBinding
import com.vesam.quiz.interfaces.OnClickListener
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.ui.view.activity.FullScreenActivity
import com.vesam.quiz.ui.view.adapter.question_cloze_list.QuestionClozeAdapter
import com.vesam.quiz.ui.viewmodel.QuizViewModel
import com.vesam.quiz.utils.application.AppQuiz
import com.vesam.quiz.utils.build_config.BuildConfig
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_CURRENT_POSITION
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_PATH
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_USER_ANSWER_LIST_ID
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_IMAGE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_TEXT
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_VIDEO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_API_TOKEN_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_QUIZ_ID_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_UUID_VALUE
import com.vesam.quiz.utils.extention.initFindFileInStorage
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


@Suppress("CAST_NEVER_SUCCEEDS")
class ClozeFragment : Fragment() {
    private lateinit var binding: FragmentClozeBinding
    private lateinit var disposableObserver: Disposable
    private lateinit var mediaPlayerAnswer: MediaPlayer
    private lateinit var details: Details
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

    override fun onResume() {
        super.onResume()
        initResumeSoundAnswer()
        initShowAnswerVideoResumed()
    }

    private fun initShowAnswerVideoResumed() {
        if (binding.lnAnswerVideoLayout.videoView.isPlaying)
            binding.lnAnswerVideoLayout.videoView.resume()
    }

    private fun initResumeSoundAnswer() {
        binding.lnAnswerSoundLayout.imgAnswerPlaySound.visibility = View.VISIBLE
        binding.lnAnswerSoundLayout.imgAnswerPauseSound.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        initShowAnswerVideoPause()
        initPauseSoundAnswer()
    }

    private fun initShowAnswerVideoPause() {
        if (binding.lnAnswerVideoLayout.videoView.isPlaying)
            binding.lnAnswerVideoLayout.videoView.pause()
    }

    private fun initAction() {
        initAdapter()
        initOnClick()
        initAnimationImage()
        initRequestQuiz()
        initOnBackPress()
    }

    private fun initAnimationImage() {
        binding.nestedScrollView.setFadingEdgeLength(170)
    }

    private fun initOnScrollChangeListener(scrollY: Int) = when {
        scrollY > 170 -> initShowCounterQuestion()
        else -> initHideCounterQuestion()
    }

    private fun initHideCounterQuestion() {
        binding.lnQuestion.visibility = View.GONE
    }

    private fun initShowCounterQuestion() {
        binding.lnQuestion.visibility = View.VISIBLE
        initSetCounterText(resultAnswerListId.size)
    }

    private fun initAdapter() {
        binding.rcCloze.layoutManager =
            LinearLayoutManager(AppQuiz.context, LinearLayoutManager.VERTICAL, false)
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
        initSetCounterText(resultAnswerListId.size)
        initStateShowSubmitBtn(resultAnswerListId.size)

    }

    @SuppressLint("SetTextI18n")
    private fun initSetCounterText(size: Int) {
        binding.txtCounter.text = "${size}/${questionClozeAdapter.itemCount}"
    }

    private fun initStateShowSubmitBtn(size: Int) = when (questionClozeAdapter.itemCount) {
        size -> initShowSubmitBtn()
        else -> binding.btnState.visibility = View.GONE
    }

    private fun initShowSubmitBtn() {
        binding.btnState.visibility = View.VISIBLE
        initScrollDown()
    }

    private fun initScrollDown() {
        binding.nestedScrollView.post {
            binding.nestedScrollView.smoothScrollTo(
                0,
                binding.nestedScrollView.getChildAt(0).height
            )
        }
        binding.nestedScrollView.viewTreeObserver
            .addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val scrollViewHeight: Int = binding.nestedScrollView.height
                    if (scrollViewHeight > 0) {
                        binding.nestedScrollView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        val lastView: View =
                            binding.nestedScrollView.getChildAt(binding.nestedScrollView.childCount - 1)
                        val lastViewBottom: Int =
                            lastView.bottom + binding.nestedScrollView.paddingBottom
                        val deltaScrollY: Int =
                            lastViewBottom - scrollViewHeight - binding.nestedScrollView.scrollY
                        binding.nestedScrollView.smoothScrollBy(
                            0,
                            deltaScrollY
                        )
                        binding.nestedScrollView.scrollBy(
                            0,
                            deltaScrollY
                        )
                    }
                }
            })
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
        binding.lnClozeImageLayout.lnImage.setOnClickListener { initFullScreenImage(false) }
        binding.lnQuestion.setOnClickListener { initFullScreenImage(true) }
        binding.btnState.setOnClickListener { initStateBtn() }
        binding.lnAnswerSoundLayout.imgAnswerPlaySound.setOnClickListener { initPlaySoundAnswer() }
        binding.lnAnswerSoundLayout.imgAnswerPauseSound.setOnClickListener { initPauseSoundAnswer() }
        binding.lnAnswerImageLayout.imgAnswer.setOnClickListener { initAnswerImage() }
        binding.lnAnswerVideoLayout.imgFullScreen.setOnClickListener { initFullScreenVideo() }
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

    private fun initFullScreenVideo() {
        val intent = Intent()
        intent.putExtra(BUNDLE_CURRENT_POSITION, initCurrentPosition())
        intent.putExtra(BUNDLE_PATH, initPath())
        intent.setClass(requireActivity(), FullScreenActivity::class.java)
        requireActivity().startActivityForResult(intent, BuildConfig.REQUEST_CODE_FULL_SCREEN)
    }

    private fun initCurrentPosition(): Int {
        val currentPosition: Int = binding.lnAnswerVideoLayout.videoView.currentPosition
        binding.lnAnswerVideoLayout.videoView.pause()
        return currentPosition
    }

    private fun initPath(): String = binding.lnAnswerVideoLayout.videoView.tag as String

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

    private fun initPlaySoundAnswer() {
        binding.lnAnswerSoundLayout.imgAnswerPlaySound.visibility = View.GONE
        binding.lnAnswerSoundLayout.imgAnswerPauseSound.visibility = View.VISIBLE
        if (::mediaPlayerAnswer.isInitialized)
            mediaPlayerAnswer.start()
    }

    private fun initStateBtn() = when (binding.btnState.text.toString()) {
        AppQuiz.context.resources.getString(R.string.submit) -> initSubmit()
        else -> initProceed()
    }

    private fun initSubmit() {
        initDispose()
        initChangeTextBtnProceed()
        initResultQuizInAdapter()
        initShowAnswer()
    }

    private fun initProceed() {
        initResultFragment()
    }

    private fun initShowAnswer() {
        if (::details.isInitialized) when (details.descriptionAnswer!!.format) {
            FORMAT_IMAGE -> initShowAnswerImage(details.descriptionAnswer)
            FORMAT_TEXT -> initShowAnswerText(details.descriptionAnswer)
            FORMAT_VIDEO -> initShowFormatVideo(details.descriptionAnswer)
            else -> initShowFormatSound(details.descriptionAnswer)
        }
    }

    private fun initShowAnswerImage(descriptionAnswer: Description?) {
        descriptionAnswer.let {
            if (it!=null){
                initResultShowAnswerImage(descriptionAnswer!!)
            }
        }
    }

    private fun initShowAnswerText(descriptionAnswer: Description?) {
        descriptionAnswer.let {
            if (it!=null){
                initResultShowAnswerText(descriptionAnswer!!.content)
            }
        }
    }

    private fun initShowFormatVideo(descriptionAnswer: Description?) {
        descriptionAnswer.let {
            if (it!=null){
                initResultShowAnswerVideo(descriptionAnswer!!)
            }
        }
    }

    private fun initResultShowAnswerVideo(descriptionAnswer: Description) {
        initShowAnswerFormatVideo()
        initVideoQuestion(descriptionAnswer.uriPath)
    }

    private fun initVideoQuestion(content: String) {
        val uri = initFindFileInStorage(content)
        binding.lnAnswerVideoLayout.videoView.tag = uri
        binding.lnAnswerVideoLayout.videoView.setVideoPath(uri)
        val mediaController = MediaController(requireActivity())
        binding.lnAnswerVideoLayout.videoView.setMediaController(mediaController)
        mediaController.setAnchorView(binding.lnAnswerVideoLayout.videoView)
        binding.lnAnswerVideoLayout.videoView.start()
    }

    private fun initShowFormatSound(descriptionAnswer: Description?) {
        descriptionAnswer.let {
            if (it!=null){
                initResultShowAnswerSound(descriptionAnswer!!)
            }
        }
    }

    private fun initResultShowAnswerImage(content: Description) {
        initShowAnswerFormatImage()
        initSetTag(binding.lnAnswerImageLayout.imgAnswer, content.urlContent)
        glideTools.displayImageOriginal(
            binding.lnAnswerImageLayout.imgAnswer,
            content.urlContent
        )
    }

    private fun initSetTag(view: View, urlContent: String) {
        view.tag = urlContent
    }

    private fun initResultShowAnswerSound(content: Description) {
        initShowAnswerFormatAudio()
        initSoundAnswer(content.uriPath)
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

    private fun releaseMPAnswer() {
        if (::mediaPlayerAnswer.isInitialized) try {
            mediaPlayerAnswer.release()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun initResultShowAnswerText(content: String?) {
        initConvertHtmlAnswer(content)
        initShowAnswerFormatText()
    }

    private fun initConvertHtmlAnswer(answer: String?) {
        answer.let {
            if (!it.isNullOrEmpty()) {
                val plainText = Html.fromHtml(it).toString()
                binding.lnAnswerTextLayout.txtTextAnswer.text = plainText
            }
        }
    }

    private fun initShowAnswerFormatImage() {
        binding.lnAnswerTextLayout.lnAnswerText.visibility=View.GONE
        binding.lnAnswerSoundLayout.lnAnswerSound.visibility=View.GONE
        binding.lnAnswerVideoLayout.cvAnswerVideo.visibility=View.GONE
        binding.lnAnswerImageLayout.lnAnswerImage.visibility=View.VISIBLE
    }

    private fun initShowAnswerFormatText() {
        binding.lnAnswerTextLayout.lnAnswerText.visibility=View.VISIBLE
        binding.lnAnswerImageLayout.lnAnswerImage.visibility=View.GONE
        binding.lnAnswerSoundLayout.lnAnswerSound.visibility=View.GONE
        binding.lnAnswerVideoLayout.cvAnswerVideo.visibility=View.GONE
    }

    private fun initShowAnswerFormatAudio() {
        binding.lnAnswerTextLayout.lnAnswerText.visibility=View.GONE
        binding.lnAnswerImageLayout.lnAnswerImage.visibility=View.GONE
        binding.lnAnswerVideoLayout.cvAnswerVideo.visibility=View.GONE
        binding.lnAnswerSoundLayout.lnAnswerSound.visibility=View.VISIBLE
    }

    private fun initShowAnswerFormatVideo() {
        binding.lnAnswerTextLayout.lnAnswerText.visibility=View.GONE
        binding.lnAnswerImageLayout.lnAnswerImage.visibility=View.GONE
        binding.lnAnswerSoundLayout.lnAnswerSound.visibility=View.GONE
        binding.lnAnswerVideoLayout.cvAnswerVideo.visibility=View.VISIBLE
    }

    private fun initResultQuizInAdapter() {
        questionClozeAdapter.initCorrectAndInCorrectItem()
    }

    private fun initChangeTextBtnSubmit() {
        binding.btnState.text = AppQuiz.context.resources.getString(R.string.submit)
    }


    private fun initChangeTextBtnProceed() {
        binding.btnState.text = AppQuiz.context.resources.getString(R.string.proceed)
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

    private fun initFullScreenImage(isShowBtnClose: Boolean) {
        val urlContent: String = binding.lnClozeImageLayout.lnImage.tag as String
        val fragmentManager: FragmentManager =
            (AppQuiz.activity as AppCompatActivity).supportFragmentManager
        val fragmentFullscreenSlider = FragmentFullscreenSliderImageCloze()
        val transaction = fragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.add(android.R.id.content, fragmentFullscreenSlider).addToBackStack(null)
            .commit()
        fragmentFullscreenSlider.setImage(urlContent, isShowBtnClose)
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
        details = it.details
        initStateQuestion(it)
        initUpdateAdapter(it)
        initPeriod(it.details.periodTime)
    }

    private fun initStateQuestion(it: ResponseQuizDetailModel) {
        if (it.details.questionDescription.format == FORMAT_IMAGE) {
            initShowImageView()
            initLoadImageQuestion(it)
            binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY: Int, _, _ ->
                initOnScrollChangeListener(
                    scrollY
                )
            })
        } else if (it.details.questionDescription.format == FORMAT_TEXT) {
            initShowTextView()
            initConvertHtmlQuestion(it.details)
        }
    }

    private fun initConvertHtmlQuestion(details: Details) {
        details.questionDescription.content.let {
            if (it!!.isNotEmpty()) {
                val plainText = Html.fromHtml(it).toString()
                binding.lnClozeTextLayout.txtQuestion.text = plainText
            }
        }
    }


    private fun initShowImageView() {
        binding.lnClozeImageLayout.lnImage.visibility = View.VISIBLE
        binding.lnClozeTextLayout.lnText.visibility = View.GONE
    }

    private fun initShowTextView() {
        binding.lnClozeImageLayout.lnImage.visibility = View.GONE
        binding.lnClozeTextLayout.lnText.visibility = View.VISIBLE
    }

    private fun initPeriod(it: Int) {
        timeDownProgressBar(binding.progressClozeTest,
            it,
            object : OnClickListener {
                override fun onClickListener() = initFinish()
            })
    }

    private fun initFinish() {
        initShowSubmitBtn()
        initChangeTextBtnSubmit()
        initResultQuizInAdapter()
    }

    private fun initUpdateAdapter(it: ResponseQuizDetailModel) {
        questionClozeAdapter.updateList(it.questions)
    }

    private fun initLoadImageQuestion(it: ResponseQuizDetailModel) {
        binding.lnClozeImageLayout.lnImage.tag = it.details.questionDescription.urlContent
        glideTools.displayImageOriginal(
            binding.lnClozeImageLayout.imgCloze,
            it.details.questionDescription.urlContent
        )
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
        (AppQuiz.activity as AppCompatActivity).onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() = initOnBackPressed()
            })
    }

    private fun initOnBackPressed() {
        AppQuiz.activity.finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == BuildConfig.REQUEST_CODE_FULL_SCREEN) initResultIntent(
            data
        )
    }

    private fun initResultIntent(data: Intent?) {
        val currentPosition = data!!.extras!!.getInt(BUNDLE_CURRENT_POSITION, 0)
        initResumeAnswerVideo(currentPosition)
    }

    private fun initResumeAnswerVideo(currentPosition: Int) {
        binding.lnAnswerVideoLayout.videoView.seekTo(currentPosition)
        binding.lnAnswerVideoLayout.videoView.start()
    }
}