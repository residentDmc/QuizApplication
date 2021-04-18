package com.vesam.quiz.ui.view.adapter.question_cloze_list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_detail.Answer
import com.vesam.quiz.data.model.quiz_detail.Question
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.ui.view.adapter.answer_cloze_list.AnswerClozeAdapter
import com.vesam.quiz.utils.extention.checkPersianCharacter
import com.vesam.quiz.utils.manager.GridLayoutCountManager

class QuestionClozeAdapter(
    private val context: Context,
    private val gridLayoutCountManager: GridLayoutCountManager
) : RecyclerView.Adapter<ViewHolderQuestionCloze>() {

    private lateinit var onClickListenerAny: OnClickListenerAny
    private lateinit var onClickListenerItemCount: OnClickListenerAny
    private val list: ArrayList<Question> = ArrayList()

    fun setOnItemClickListener(onClickListenerAny: OnClickListenerAny) {
        this.onClickListenerAny = onClickListenerAny
    }

    fun setOnItemCountClickListener(onClickListenerItemCount: OnClickListenerAny) {
        this.onClickListenerItemCount = onClickListenerItemCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderQuestionCloze =
        ViewHolderQuestionCloze(getViewHolder(parent))

    private fun getViewHolder(parent: ViewGroup): View = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_question_cloze, parent, false)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolderQuestionCloze: ViewHolderQuestionCloze, position: Int) {
        val question = list[position]
        checkPersianCharacter(
            question.title,
            viewHolderQuestionCloze.lnTitle
        )
        viewHolderQuestionCloze.txtTitle.text = question.title
        initAdapter(viewHolderQuestionCloze.rcAnswerCloze, question.answers)
    }

    private fun initAdapter(
        rcAnswerCloze: RecyclerView,
        answers: ArrayList<Answer>
    ) {
        val answerClozeAdapter = AnswerClozeAdapter(context)
        rcAnswerCloze.layoutManager =
            GridLayoutManager(context, gridLayoutCountManager.getColumnWidthAnswer())
        rcAnswerCloze.setHasFixedSize(true)
        rcAnswerCloze.adapter = answerClozeAdapter
        answerClozeAdapter.updateList(answers)
        answerClozeAdapter.setOnItemClickListener(object : OnClickListenerAny {
            override fun onClickListener(any: Any) = initItemClick(any)
        })
    }

    private fun initItemClick(any: Any) = onClickListenerAny.onClickListener(any)


    override fun getItemCount(): Int = list.size

    fun updateList(listQuestion: List<Question>) {
        list.clear()
        list.addAll(listQuestion)
        notifyDataSetChanged()
    }

    fun updateIndexList(answer: Answer) = list.forEach {
        initItemQuestion(it, answer)
    }

    private fun initItemQuestion(question: Question, answer: Answer) {
        val indexOf = question.answers.indexOf(answer)
        val validIndexOf = indexOf > -1
        val validSelect = initCheckAnswer(question, answer)
        val resultValid = validSelect && validIndexOf
        when {
            resultValid -> initResultIndexOf(question, answer, indexOf)
        }
    }

    private fun initCheckAnswer(question: Question, answer: Answer): Boolean {
        var resultValid = false
        question.answers.forEach { if (it.isSelected) resultValid = it.isSelected }
        return when {
            answer.isSelected -> answer.isSelected
            else -> !resultValid
        }
    }

    private fun initResultIndexOf(question: Question, answer: Answer, indexOf: Int) {
        answer.isSelected = !answer.isSelected
        question.answers[indexOf] = answer
        initCallBackItemCount(answer)
        notifyDataSetChanged()
    }

    private fun initCallBackItemCount(answer: Answer) =
        onClickListenerItemCount.onClickListener(answer)

}