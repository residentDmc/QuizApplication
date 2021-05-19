package com.vesam.quiz.ui.view.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.*
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.Progress
import com.google.gson.Gson
import com.vesam.quiz.R
import com.vesam.quiz.data.model.file_download.FileDownload
import com.vesam.quiz.data.model.quiz_detail.*
import com.vesam.quiz.databinding.ActivityQuizBinding
import com.vesam.quiz.ui.viewmodel.QuizViewModel
import com.vesam.quiz.utils.base.BaseActivity
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BASE_URL
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BASE_URL_IMAGE_AND_VIDEO_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_AUDIO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.FORMAT_VIDEO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MIM_TYPE_AUDIO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MIM_TYPE_VIDEO
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.MULTIMEDIA
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.REQUEST_ID_MULTIPLE_PERMISSIONS
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_API_TOKEN_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_QUIZ_ID_VALUE
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.USER_UUID_VALUE
import com.vesam.quiz.utils.extention.getDirPath
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_BASE_URL_IMAGE_AND_VIDEO_VALUE
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_BASE_URL_VALUE
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_QUIZ_ID_VALUE
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_USER_API_TOKEN_VALUE
import com.vesam.quiz.utils.option.Option.Companion.BUNDLE_USER_UUID_VALUE
import com.vesam.quiz.utils.tools.HandelErrorTools
import com.vesam.quiz.utils.tools.ThrowableTools
import com.vesam.quiz.utils.tools.ToastTools
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


@Suppress("DEPRECATION")
class QuizActivity : BaseActivity() {

    private lateinit var binding: ActivityQuizBinding
    private lateinit var navController: NavController
    private val toastTools: ToastTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val handelErrorTools: HandelErrorTools by inject()
    private val quizViewModel: QuizViewModel by viewModel()
    private val urlList: ArrayList<FileDownload> = ArrayList()
    private var progressBarStatus = 0
    private var counterFile = 0
    private var dummy: Int = 0
    private var downloadId: Int = 0
    private val requiredPermissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            initPermissions()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun initPermissions() {
        when {
            !checkAndRequestPermissions() -> finish()
            else -> initAction()
        }
    }

    private fun checkAndRequestPermissions(): Boolean {
        val neededPermissions: List<String> = getNeededPermissions()
        if (neededPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                neededPermissions.toTypedArray(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
            return false
        }
        return true
    }

    private fun getNeededPermissions(): List<String> {
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in requiredPermissions) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) listPermissionsNeeded.add(permission)
            }
        }
        return listPermissionsNeeded
    }

    private fun initAction() {
        initBundle()
        initNavController()
        initRequest()
    }

    private fun initBundle() {
        BASE_URL = intent.extras!!.getString(BUNDLE_BASE_URL_VALUE, "")
        BASE_URL_IMAGE_AND_VIDEO_VALUE = intent.extras!!.getString(
            BUNDLE_BASE_URL_IMAGE_AND_VIDEO_VALUE, ""
        )
        USER_API_TOKEN_VALUE = intent.extras!!.getString(BUNDLE_USER_API_TOKEN_VALUE, "")
        USER_UUID_VALUE = intent.extras!!.getString(BUNDLE_USER_UUID_VALUE, "")
        USER_QUIZ_ID_VALUE = intent.extras!!.getInt(BUNDLE_QUIZ_ID_VALUE, -1)
    }

    private fun initNavController() {
        navController = Navigation.findNavController(this, R.id.my_nav_fragment)
    }

    private fun initRequest() {
        initShowLoading()
        initShowProgressLoading()
        quizViewModel.initQuizDetail(
            USER_UUID_VALUE,
            USER_API_TOKEN_VALUE,
            USER_QUIZ_ID_VALUE
        ).observe(
            this,
            this::initResultQuiz
        )
    }

    private fun initShowLoading() {
        binding.lnNavFragment.visibility = View.GONE
        binding.lnLoading.lnParent.visibility = View.VISIBLE
    }

    private fun initHideLoading() {
        binding.lnNavFragment.visibility = View.VISIBLE
        binding.lnLoading.lnParent.visibility = View.GONE
    }

    private fun initShowProgressLoading() {
        binding.lnLoading.lnProgress.visibility = View.INVISIBLE
        binding.lnLoading.progressBarLoading.visibility = View.VISIBLE
    }

    private fun initHideProgressLoading() {
        binding.lnLoading.lnProgress.visibility = View.VISIBLE
        binding.lnLoading.progressBarLoading.visibility = View.GONE
    }

    private fun initHideProgressAndCounterLoading() {
        binding.lnLoading.lnProgress.visibility = View.VISIBLE
        binding.lnLoading.progressBarLoading.visibility = View.GONE
        binding.lnLoading.txtCounter.visibility = View.GONE
    }

    private fun initSingleHideProgressLoading() {
        binding.lnLoading.progressBarLoading.visibility = View.GONE
    }

    private fun initResultQuiz(it: Any) {
        when (it) {
            is ResponseQuizDetailModel -> initDelayQuizDetailModel(it)
            is Throwable -> initThrowable(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initDelayQuizDetailModel(it: ResponseQuizDetailModel) {
        initDetail(it.details)
        it.questions.forEach(this::initQuestions)
        initCounter(counterFile, urlList.size)
        initDownloadFile(it)
    }

    private fun initDetail(details: Details) = details.descriptionAnswer.let {
        when {
            it!=null -> initResultDescriptionAnswer(it)
        }
    }

    private fun initResultDescriptionAnswer(it: Description) {
        val fileDownload =
            FileDownload(
                "",
                it.format,
                it.urlContent
            )
        initAddList(fileDownload)
    }

    private fun initQuestions(question: Question) {
        question.quizDescription.let { if (it != null) initQuestionsResult(question) }
        initQuestionList(question)
    }

    private fun initQuestionsResult(question: Question) {
        when (question.quizDescription!!.format) {
            FORMAT_VIDEO -> initQuestionsFormatVideo(question)
            FORMAT_AUDIO -> initQuestionsFormatAudio(question)
        }
    }

    private fun initQuestionsFormatAudio(question: Question) {
        val fileDownload = FileDownload(
            question.title,
            question.quizDescription!!.format,
            question.quizDescription.urlContent
        )
        initAddList(fileDownload)
    }

    private fun initAddList(fileDownload: FileDownload) {
        val hasFile = getAllFileInStorage(initFileDownload(fileDownload))
        if (!hasFile)
            urlList.add(fileDownload)

    }

    private fun initFileDownload(fileDownload: FileDownload): String {
        return when (fileDownload.format) {
            FORMAT_VIDEO -> "${nameFileEncrypt(fileDownload.url)}${MIM_TYPE_VIDEO}"
            else -> "${nameFileEncrypt(fileDownload.url)}${MIM_TYPE_AUDIO}"
        }
    }

    private fun nameFileEncrypt(filename: String): String {
        val lastSlashChar = filename.lastIndexOf("/")
        val lastSlashChars = filename.lastIndexOf(".mp4")
        val lastSlashChara = filename.lastIndexOf(".mp3")
        return when {
            lastSlashChars > -1 -> filename.substring(lastSlashChar + 1, lastSlashChars - 1)
            lastSlashChara > -1 -> filename.substring(lastSlashChar + 1, lastSlashChara - 1)
            else -> filename.substring(lastSlashChar + 1)
        }
    }

    private fun getAllFileInStorage(name: String): Boolean {
        val resultFile: ArrayList<File> = ArrayList()
        val dic = getDirPath()
        val parentDir = File(dic)
        if (!parentDir.exists()) parentDir.mkdir()
        val files: Queue<File> =
            LinkedList(listOf(*Objects.requireNonNull(parentDir.listFiles())))
        resultFile.addAll(files)
        return when {
            resultFile.isEmpty() -> false
            else -> initCheckFile(name, resultFile)
        }
    }

    private fun initCheckFile(name: String, files: ArrayList<File>): Boolean {
        var hasFile = false
        for (index in 0 until files.size) {
            val fileName = files[index].name
            hasFile = (name == fileName)
            if (hasFile) break
        }
        return hasFile
    }

    private fun initQuestionsFormatVideo(question: Question) {
        val fileDownload = FileDownload(
            question.title,
            question.quizDescription!!.format,
            question.quizDescription.urlContent
        )
        initAddList(fileDownload)
    }

    private fun initQuestionList(question: Question) {
        question.answers.forEach { initCheckDescription(it) }
    }

    private fun initCheckDescription(answer: Answer) {
        answer.description.let { if (it != null) initAnswerResult(answer) }
    }

    private fun initAnswerResult(answer: Answer) {
        when (answer.description!!.format) {
            FORMAT_VIDEO -> initAnswerFormatVideo(answer)
            FORMAT_AUDIO -> initAnswerFormatAudio(answer)
        }
    }

    private fun initAnswerFormatVideo(answer: Answer) {
        val fileDownload =
            FileDownload(
                answer.title,
                answer.description!!.format,
                answer.description.urlContent
            )
        initAddList(fileDownload)
    }

    private fun initAnswerFormatAudio(answer: Answer) {
        val fileDownload =
            FileDownload(
                answer.title,
                answer.description!!.format,
                answer.description.urlContent
            )
        initAddList(fileDownload)
    }

    private fun initDownloadFile(responseQuizDetailModel: ResponseQuizDetailModel) = when {
        urlList.isEmpty() -> initLoading(responseQuizDetailModel)
        else -> initProcessing(responseQuizDetailModel)
    }

    @SuppressLint("SetTextI18n")
    private fun initLoading(it: ResponseQuizDetailModel) {
        initHideProgressAndCounterLoading()
        binding.lnLoading.txtProgress.text = "${progressBarStatus}%"
        Thread {
            while (progressBarStatus < 100) {
                try {
                    dummy += 25
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progressBarStatus = dummy
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.lnLoading.txtProgress.text = "${progressBarStatus}%"
                }, 100)
                binding.lnLoading.progressBar.progress = progressBarStatus
            }
            Handler(Looper.getMainLooper()).postDelayed({
                initQuizDetailModel(it)
            }, 100)
        }.start()

    }

    private fun initProcessing(responseQuizDetailModel: ResponseQuizDetailModel) {
        initHideProgressLoading()
        val fileDownload = urlList.first()
        initResultDownload(fileDownload, responseQuizDetailModel)
    }

    private fun initResultDownload(
        it: FileDownload,
        responseQuizDetailModel: ResponseQuizDetailModel
    ) {
        downloadId = PRDownloader.download(it.url, getDirPath(), initFileDownload(it))
            .build()
            .setOnProgressListener { initOnProgress(it) }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() = initDownloadComplete(
                    it,
                    responseQuizDetailModel
                )

                override fun onError(error: com.downloader.Error?) {
                    error!!.connectionException.let {
                        if (it != null) {
                            handelErrorTools.handelError(
                                it
                            )
                            toastTools.toast(it.message.toString())
                        }
                    }
                }
            })
    }

    private fun initFinish() {
        finish()
    }

    private fun initDownloadComplete(
        it: FileDownload,
        responseQuizDetailModel: ResponseQuizDetailModel
    ) {
        initCheckList(it, responseQuizDetailModel)
        initCheckFinal(responseQuizDetailModel)
    }

    private fun initCheckFinal(responseQuizDetailModel: ResponseQuizDetailModel) {
        if (counterFile == urlList.size) initQuizDetailModel(responseQuizDetailModel)
    }

    private fun initCheckList(
        it: FileDownload,
        responseQuizDetailModel: ResponseQuizDetailModel
    ) {
        val indexOf = urlList.indexOf(it)
        val step = indexOf + 1
        if (step < urlList.size) {
            val fileDownload = urlList[step]
            initResultDownload(fileDownload, responseQuizDetailModel)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initOnProgress(it: Progress?) {
        val progress = (it!!.currentBytes * 100 / it.totalBytes).toInt()
        binding.lnLoading.txtProgress.text = "${progress}%"
        binding.lnLoading.progressBar.progress = progress
        if (progress == 100) {
            ++counterFile
            initCounter(counterFile, urlList.size)
        }
    }


    @SuppressLint("SetTextI18n")
    private fun initCounter(count: Int, final: Int) {
        binding.lnLoading.txtCounter.text = "${count}/${final}"
    }

    private fun initThrowable(it: Throwable) {
        val message = throwableTools.getThrowableError(it)
        handelErrorTools.handelError(it)
        toastTools.toast(message)
        finish()
    }

    private fun initQuizDetailModel(it: ResponseQuizDetailModel) {
        initHideLoading()
        initSingleHideProgressLoading()
        when (it.details.type) {
            MULTIMEDIA -> initMultimediaQuiz()
            else -> initClozeQuiz()
        }
    }

    private fun initClozeQuiz() {
        navController.setGraph(R.navigation.nav_graph_cloze)
    }

    private fun initMultimediaQuiz() {
        navController.setGraph(R.navigation.nav_graph_quiz)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_ID_MULTIPLE_PERMISSIONS -> initRequestCode(grantResults)
        }
    }

    private fun initRequestCode(grantResults: IntArray) {
        when {
            requiredPermissions.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED -> initAction()
            else -> finish()
        }
        return
    }

    override fun onDestroy() {
        super.onDestroy()
        PRDownloader.pause(downloadId)
        PRDownloader.cancel(downloadId)
    }
}