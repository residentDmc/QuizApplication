package com.vesam.quiz.utils.manager

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import com.vesam.quiz.utils.application.AppQuiz


class KeyboardManager {

    fun hideKeyboard() {
        val v = AppQuiz.activity.currentFocus
        val imm = AppQuiz.activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (v != null)
            imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    private fun showKeyboard() {
        val v = AppQuiz.activity.currentFocus
        val imm = AppQuiz.activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (v != null)
            imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
    }

}