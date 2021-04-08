package com.vesam.quiz.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vesam.quiz.databinding.FragmentDashboardBinding
import com.vesam.quiz.utils.option.Option

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAction()
    }

    private fun initAction() {
        initOnClick()
    }

    private fun initOnClick() {
        binding.btnEnterExam.setOnClickListener { initIntent() }
    }

    private fun initIntent() {
        val baseUrl= binding.tlBaseUrl.editText!!.text.toString()
        val token= binding.tlToken.editText!!.text.toString()
        val userId= binding.tlUserId.editText!!.text.toString()
        val quizId= binding.tlQuizId.editText!!.text.toString().toInt()
        Option.start(requireContext(),baseUrl,token,userId,quizId)
    }

}