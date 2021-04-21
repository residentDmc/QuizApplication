@file:Suppress("DEPRECATION")

package com.vesam.quiz.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import com.vesam.quiz.R
import com.vesam.quiz.databinding.FragmentFullscreenSliderImageClozeBinding
import com.vesam.quiz.utils.tools.GlideTools
import com.vesam.quiz.utils.tools.HandelErrorTools
import org.koin.android.ext.android.inject

@Suppress("DEPRECATION")
class FragmentFullscreenSliderImageCloze : DialogFragment() {

    private lateinit var binding: FragmentFullscreenSliderImageClozeBinding
    private val handelErrorTools: HandelErrorTools by inject()
    private val glideTools: GlideTools by inject()
    private lateinit var image: String
    private var isShowBtnClose: Boolean = false

    fun setImage(image: String,isShowBtnClose:Boolean) {
        this.image = image
        this.isShowBtnClose = isShowBtnClose
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFullscreenSliderImageClozeBinding.inflate(layoutInflater)
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
        initSetView()
        initStateShowBtn()
        initOnClick()
        initOnBackPress()
    }

    private fun initStateShowBtn() = when {
        isShowBtnClose -> binding.imgBtnClose.visibility=View.VISIBLE
        else -> binding.imgBtnClose.visibility=View.GONE
    }

    private fun initSetView() {
        glideTools.displayImageSliderZoom(binding.zoomView, image)
    }

    private fun initOnClick() {
        binding.imgBtnClose.setOnClickListener { dismiss() }
    }

    private fun initOnBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    dismiss()
                }
            })
    }
}