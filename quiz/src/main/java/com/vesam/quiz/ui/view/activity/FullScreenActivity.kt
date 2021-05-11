package com.vesam.quiz.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.MediaController
import com.vesam.quiz.R
import com.vesam.quiz.databinding.ActivityFullScreenBinding
import com.vesam.quiz.databinding.ActivityQuizBinding
import com.vesam.quiz.utils.build_config.BuildConfig
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_CURRENT_POSITION
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.BUNDLE_PATH
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.REQUEST_CODE_FULL_SCREEN
import com.vesam.quiz.utils.extention.initFindFileInStorage
import com.vesam.quiz.utils.tools.GlideTools
import com.vesam.quiz.utils.tools.HandelErrorTools
import com.vesam.quiz.utils.tools.ThrowableTools
import com.vesam.quiz.utils.tools.ToastTools
import org.koin.android.ext.android.inject

@Suppress("DEPRECATION")
class FullScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullScreenBinding
    private val handelErrorTools: HandelErrorTools by inject()
    private var currentPosition = 0
    private var path = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       initFullScreen()
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            initAction()
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun initFullScreen() {
        val layoutParam=window.attributes
        layoutParam.flags=WindowManager.LayoutParams.FLAG_FULLSCREEN
        window.attributes=layoutParam
    }

    private fun initAction() {
        getIntentExtra()
        initVideoView()
        initOnClick()
    }

    private fun initOnClick() {
        binding.imgExitFullScreen.setOnClickListener { initExitFullScreen() }
    }

    private fun initExitFullScreen() {
        val intent = Intent()
        intent.putExtra(BUNDLE_CURRENT_POSITION, binding.videoView.currentPosition)
        setResult(RESULT_OK,intent)
        finish()
    }

    private fun initVideoView() {
        binding.videoView.setVideoPath(path)
        val mediaController = MediaController(this)
        binding.videoView.setMediaController(mediaController)
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.seekTo(currentPosition)
        binding.videoView.start()
    }

    private fun getIntentExtra() {
        path = intent.extras!!.getString(BUNDLE_PATH, "")
        currentPosition = intent.extras!!.getInt(BUNDLE_CURRENT_POSITION, 0)
    }

    override fun onBackPressed() {
        initExitFullScreen()
    }
}