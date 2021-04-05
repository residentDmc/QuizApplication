package com.vesam.quiz.ui.view.adapter.quiz_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_list.Quiz
import com.vesam.quiz.interfaces.OnClickListenerAny
import java.util.*

class QuizAdapter : RecyclerView.Adapter<ViewHolderQuiz>() {

    lateinit var onClickListenerAny : OnClickListenerAny
    private val list: ArrayList<Quiz> = ArrayList()

    fun setOnItemClickListener( onClickListenerAny : OnClickListenerAny){
        this.onClickListenerAny = onClickListenerAny
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderQuiz =
        ViewHolderQuiz(getViewHolder(parent))

    private fun getViewHolder(parent: ViewGroup): View = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_quiz, parent, false)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolderQuiz: ViewHolderQuiz, position: Int) {
        val quiz = list[position]
        val number=position+1
        viewHolderQuiz.txtNumber.text = "$number."
        viewHolderQuiz.txtTitle.text = quiz.title
        viewHolderQuiz.lnParent.setOnClickListener { onClickListenerAny.onClickListener(quiz) }
    }

    override fun getItemCount(): Int = list.size

    fun updateList(listQuiz: List<Quiz>) {
        list.clear()
        list.addAll(listQuiz)
        notifyDataSetChanged()
    }
}