package com.vesam.quiz.ui.view.adapter.answer_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_detail.Answer
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.utils.extention.checkPersianCharacter
import java.util.*

class AnswerAdapter : RecyclerView.Adapter<ViewHolderAnswer>() {

    lateinit var onClickListenerAny: OnClickListenerAny
    private val list: ArrayList<Answer> = ArrayList()

    fun setOnItemClickListener(onClickListenerAny: OnClickListenerAny) {
        this.onClickListenerAny = onClickListenerAny
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAnswer =
        ViewHolderAnswer(getViewHolder(parent))

    private fun getViewHolder(parent: ViewGroup): View = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_answer, parent, false)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolderAnswer: ViewHolderAnswer, position: Int) {
        val answer = list[position]
        checkPersianCharacter(
            answer.title,
            viewHolderAnswer.lnParentStart,
            viewHolderAnswer.lnParentEnd
        )
        initStateCorrect(viewHolderAnswer, answer)
        initEnabled(viewHolderAnswer, answer)
        viewHolderAnswer.txtTitleStart.text = answer.title
        viewHolderAnswer.txtTitleEnd.text = answer.title
        viewHolderAnswer.lnParentStart.setOnClickListener {
            onClickListenerAny.onClickListener(
                answer
            )
        }
        viewHolderAnswer.lnParentEnd.setOnClickListener { onClickListenerAny.onClickListener(answer) }
    }

    private fun initEnabled(viewHolderAnswer: ViewHolderAnswer, answer: Answer) {
        viewHolderAnswer.lnParentStart.isEnabled = !answer.isEnable
        viewHolderAnswer.lnParentEnd.isEnabled = !answer.isEnable
    }

    private fun initStateCorrect(viewHolderAnswer: ViewHolderAnswer, answer: Answer) {
        when (answer.isSuccess) {
            0 -> initDefault(viewHolderAnswer)
            1 -> initSuccess(viewHolderAnswer)
            2 -> initUnSuccess(viewHolderAnswer)
        }
    }

    private fun initDefault(viewHolderAnswer: ViewHolderAnswer) {
        viewHolderAnswer.imgBtnSuccessStart.visibility = View.GONE
        viewHolderAnswer.imgBtnUnSuccessStart.visibility = View.GONE
        viewHolderAnswer.lnParentStart.setBackgroundResource(R.drawable.rounded_white_shape)


        viewHolderAnswer.imgBtnSuccessEnd.visibility = View.GONE
        viewHolderAnswer.imgBtnUnSuccessEnd.visibility = View.GONE
        viewHolderAnswer.lnParentEnd.setBackgroundResource(R.drawable.rounded_white_shape)



    }

    private fun initSuccess(viewHolderAnswer: ViewHolderAnswer) {
        viewHolderAnswer.imgBtnSuccessStart.visibility = View.VISIBLE
        viewHolderAnswer.imgBtnUnSuccessStart.visibility = View.GONE
        viewHolderAnswer.lnParentStart.setBackgroundResource(R.drawable.rounded_green_shape)

        viewHolderAnswer.imgBtnSuccessEnd.visibility = View.VISIBLE
        viewHolderAnswer.imgBtnUnSuccessEnd.visibility = View.GONE
        viewHolderAnswer.lnParentEnd.setBackgroundResource(R.drawable.rounded_green_shape)
    }

    private fun initUnSuccess(viewHolderAnswer: ViewHolderAnswer) {
        viewHolderAnswer.imgBtnSuccessStart.visibility = View.GONE
        viewHolderAnswer.imgBtnUnSuccessStart.visibility = View.VISIBLE
        viewHolderAnswer.lnParentStart.setBackgroundResource(R.drawable.rounded_red_shape)

        viewHolderAnswer.imgBtnSuccessEnd.visibility = View.GONE
        viewHolderAnswer.imgBtnUnSuccessEnd.visibility = View.VISIBLE
        viewHolderAnswer.lnParentEnd.setBackgroundResource(R.drawable.rounded_red_shape)
    }

    override fun getItemCount(): Int = list.size

    fun updateList(listAnswer: List<Answer>) {
        list.clear()
        list.addAll(listAnswer)
        notifyDataSetChanged()
    }

    fun answerSuccessQuestion(answer: Answer) {
        val indexOf = list.indexOf(answer)
        answer.isSuccess = 1
        list[indexOf] = answer
        list.forEach { it.isEnable = true }
        notifyDataSetChanged()
    }

    fun answerUnSuccessQuestion(answer: Answer) {
        val indexOf = list.indexOf(answer)
        answer.isSuccess = 2
        list[indexOf] = answer
        list.forEach { it.isEnable = true }
        notifyDataSetChanged()
    }
}