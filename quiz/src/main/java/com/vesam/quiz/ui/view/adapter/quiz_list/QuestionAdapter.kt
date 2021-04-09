package com.vesam.quiz.ui.view.adapter.quiz_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_detail.Question
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.utils.extention.checkPersianCharacter
import java.util.*

class QuestionAdapter : RecyclerView.Adapter<ViewHolderQuestion>() {

    lateinit var onClickListenerAny : OnClickListenerAny
    private val list: ArrayList<Question> = ArrayList()

    fun setOnItemClickListener( onClickListenerAny : OnClickListenerAny){
        this.onClickListenerAny = onClickListenerAny
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderQuestion =
        ViewHolderQuestion(getViewHolder(parent))

    private fun getViewHolder(parent: ViewGroup): View = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_quiz, parent, false)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolderQuestion: ViewHolderQuestion, position: Int) {
        val quiz = list[position]
        viewHolderQuestion.txtTitle.text = quiz.title
        checkPersianCharacter(quiz.title,viewHolderQuestion.lnParent)
        viewHolderQuestion.lnParent.setOnClickListener { onClickListenerAny.onClickListener(quiz) }
    }

    override fun getItemCount(): Int = list.size

    fun updateList(listQuiz: List<Question>) {
        list.clear()
        list.addAll(listQuiz)
        notifyDataSetChanged()
    }
}