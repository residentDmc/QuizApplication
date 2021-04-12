package com.vesam.quiz.ui.view.adapter.answer_result_list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_detail.Answer
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.utils.extention.checkPersianCharacter
import java.util.*

class AnswerResultAdapter(private val context: Context) :
    RecyclerView.Adapter<ViewHolderAnswerResult>() {

    lateinit var onClickListenerAny: OnClickListenerAny
    private val list: ArrayList<Answer> = ArrayList()

    fun setOnItemClickListener(onClickListenerAny: OnClickListenerAny) {
        this.onClickListenerAny = onClickListenerAny
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAnswerResult =
        ViewHolderAnswerResult(getViewHolder(parent))

    private fun getViewHolder(parent: ViewGroup): View = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_result_answer, parent, false)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolderAnswer: ViewHolderAnswerResult, position: Int) {
        val answer = list[position]
        val number = position + 1
        initStateCorrect(viewHolderAnswer, answer)
        viewHolderAnswer.txtTitle.text =
            context.resources.getString(R.string.question_number) + " " + number
        //viewHolderAnswer.lnParent.setOnClickListener { onClickListenerAny.onClickListener(answer) }
    }

    private fun initStateCorrect(viewHolderAnswer: ViewHolderAnswerResult, answer: Answer) {
        when (answer.isSuccess) {
            0 -> initDefault(viewHolderAnswer)
            1 -> initSuccess(viewHolderAnswer)
            2 -> initUnSuccess(viewHolderAnswer)
        }
    }

    private fun initDefault(viewHolderAnswer: ViewHolderAnswerResult) {
        viewHolderAnswer.imgSuccess.visibility = View.GONE
        viewHolderAnswer.imgUnSuccess.visibility = View.INVISIBLE
        viewHolderAnswer.lnParent.setBackgroundResource(R.drawable.rounded_white_shape)
    }

    private fun initSuccess(viewHolderAnswer: ViewHolderAnswerResult) {
        viewHolderAnswer.imgSuccess.visibility = View.VISIBLE
        viewHolderAnswer.imgUnSuccess.visibility = View.GONE
        viewHolderAnswer.lnParent.setBackgroundResource(R.drawable.rounded_green_shape)
    }

    private fun initUnSuccess(viewHolderAnswer: ViewHolderAnswerResult) {
        viewHolderAnswer.imgSuccess.visibility = View.GONE
        viewHolderAnswer.imgUnSuccess.visibility = View.VISIBLE
        viewHolderAnswer.lnParent.setBackgroundResource(R.drawable.rounded_red_shape)
    }

    override fun getItemCount(): Int = list.size

    fun updateList(listAnswer: List<Answer>) {
        list.clear()
        list.addAll(listAnswer)
        notifyDataSetChanged()
    }
}