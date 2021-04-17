@file:Suppress("DEPRECATION")

package com.vesam.quiz.ui.view.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.vesam.quiz.R
import com.vesam.quiz.databinding.FragmentFullscreenSliderBinding
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.ui.view.adapter.gallery.AdapterGalleryImage
import com.vesam.quiz.ui.view.adapter.gallery.AdapterSlidingImage
import com.vesam.quiz.utils.image.ZoomOutSlideTransformer
import com.vesam.quiz.utils.tools.GlideTools
import com.vesam.quiz.utils.tools.HandelErrorTools
import org.koin.android.ext.android.inject
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class FragmentFullscreenSlider : DialogFragment() {

    private lateinit var binding: FragmentFullscreenSliderBinding
    private val handelErrorTools: HandelErrorTools by inject()
    private val glideTools: GlideTools by inject()
    private val adapterGalleryImage: AdapterGalleryImage by inject()
    private val zoomOutSlideTransformer: ZoomOutSlideTransformer by inject()
    private val adapterSlidingImage: AdapterSlidingImage by inject()
    private var imageLists:ArrayList<String> = ArrayList()
    private var currentPage = 0
    private var selected = 0

    fun setImage(image: String) {
        this.imageLists.clear()
        this.imageLists.add(image)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFullscreenSliderBinding.inflate(layoutInflater)
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
        initOnBackPress()
        initGallery()
        initSlider()
    }

    private fun initOnClick() {
        binding.imgBtnClose.setOnClickListener {  dismiss() }
    }

    private fun initGallery() {
        try {
            binding.gallery.adapter = adapterGalleryImage
            val handler = Handler()
            val update = Runnable { binding.pager.setCurrentItem(currentPage, true) }
            binding.gallery.setSelection(selected, true)
            binding.gallery.setSpacing(18)
            binding.gallery.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long,
                ) {
                    currentPage = position
                    handler.post(update)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun initSlider() {
        try {
            sliderConfig()
            binding.pager.addOnPageChangeListener(object : OnPageChangeListener {
                override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
                override fun onPageSelected(i: Int) {
                    binding.gallery.setSelection(i, true)
                }

                override fun onPageScrollStateChanged(i: Int) {}
            })
            adapterSlidingImage.setOnclick(object : OnClickListenerAny {
                override fun onClickListener(any: Any) = toggleSmallSlider()
            })

        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }

    private fun sliderConfig() {
        adapterSlidingImage.setAdapterSlidingImage(context,
            imageLists,
            false,
            glideTools,
            handelErrorTools,
            true)
        binding.pager.adapter = adapterSlidingImage
        binding.pager.setCurrentItem(selected, true)
        binding.pager.setPageTransformer(true, zoomOutSlideTransformer)
    }

    private fun toggleSmallSlider() {
        binding.gallery.visibility = if (binding.gallery.visibility == View.GONE) View.VISIBLE else View.GONE
        binding.gallery.startAnimation(
            if (binding.gallery.visibility == View.GONE) AnimationUtils.loadAnimation(
                context, R.anim.move_up) else AnimationUtils.loadAnimation(context,
                R.anim.move_down)
        )
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