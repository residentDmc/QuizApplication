package com.vesam.quiz.ui.view.adapter.quiz_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_list.Quiz
import java.util.*

class QuizAdapter : RecyclerView.Adapter<ViewHolderQuiz>() {

    private val list: ArrayList<Quiz> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderQuiz =
        ViewHolderQuiz(getViewHolder(parent))

    private fun getViewHolder(parent: ViewGroup): View = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_quiz, parent, false)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holderSheet: ViewHolderQuiz, position: Int) {
        val quiz = list[position]
        val number=position+1
        holderSheet.txtNumber.text = "$number."
        holderSheet.txtTitle.text = quiz.title
    }

    override fun getItemCount(): Int = list.size

    fun updateList(listQuiz: List<Quiz>) {
        list.clear()
        list.addAll(listQuiz)
        notifyDataSetChanged()
    }
}