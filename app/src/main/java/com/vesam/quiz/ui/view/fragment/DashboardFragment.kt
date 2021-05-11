package com.vesam.quiz.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.vesam.quiz.R
import com.vesam.quiz.utils.option.Option

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction(view)
    }

    private fun initAction(view: View) {
        initOnClick(view)
    }

    private fun initOnClick(view: View) {
        val btnEnterExam=view.findViewById<Button>(R.id.btnEnterExam)
        btnEnterExam.setOnClickListener { initIntentQuizActivity(view) }
    }

    private fun initIntentQuizActivity(view: View) {
        val tlBaseUrl=view.findViewById<TextInputLayout>(R.id.tlBaseUrl)
        val tlBaseUrlImageAndVideo=view.findViewById<TextInputLayout>(R.id.tlBaseUrlImageAndVideo)
        val tlToken=view.findViewById<TextInputLayout>(R.id.tlToken)
        val tlUserId=view.findViewById<TextInputLayout>(R.id.tlUserId)
        val tlQuizId=view.findViewById<TextInputLayout>(R.id.tlQuizId)
        val baseUrl= tlBaseUrl.editText!!.text.toString()
        val baseUrlImageAndVideo= tlBaseUrlImageAndVideo.editText!!.text.toString()
        val token= tlToken.editText!!.text.toString()
        val userId= tlUserId.editText!!.text.toString()
        val quizId= tlQuizId.editText!!.text.toString().toInt()
        Option.start(requireActivity(),baseUrl,baseUrlImageAndVideo,token,userId,quizId)
    }
}