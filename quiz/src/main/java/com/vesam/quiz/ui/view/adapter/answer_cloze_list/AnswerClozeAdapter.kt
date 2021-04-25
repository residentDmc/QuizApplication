package com.vesam.quiz.ui.view.adapter.answer_cloze_list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_detail.Answer
import com.vesam.quiz.data.model.quiz_detail.Question
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.ui.view.adapter.answer_quiz_list.ViewHolderAnswer
import com.vesam.quiz.utils.extention.checkPersianCharacter
import com.vesam.quiz.utils.extention.setCircleColor
import java.util.*

@Suppress("DEPRECATION")
class AnswerClozeAdapter(private val context: Context) :
    RecyclerView.Adapter<ViewHolderAnswerCloze>() {

    private lateinit var onClickListenerAny: OnClickListenerAny
    private val list: ArrayList<Answer> = ArrayList()

    fun setOnItemClickListener(onClickListenerAny: OnClickListenerAny) {
        this.onClickListenerAny = onClickListenerAny
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAnswerCloze =
        ViewHolderAnswerCloze(getViewHolder(parent))

    private fun getViewHolder(parent: ViewGroup): View = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_answer_cloze, parent, false)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolderAnswerCloze: ViewHolderAnswerCloze, position: Int) {
        val answer = list[position]
        checkPersianCharacter(
            answer.title,
            viewHolderAnswerCloze.lnParentStart,
            viewHolderAnswerCloze.lnParentEnd
        )
        initTitle(viewHolderAnswerCloze,answer)
        initState(viewHolderAnswerCloze, answer)
        initIsEnabled(viewHolderAnswerCloze, answer)
    }

    private fun initTitle(viewHolderAnswerCloze: ViewHolderAnswerCloze, answer: Answer) {
        viewHolderAnswerCloze.txtTitleEnd.text = answer.title
        viewHolderAnswerCloze.txtTitleStart.text = answer.title
    }

    private fun initIsEnabled(viewHolderAnswerCloze: ViewHolderAnswerCloze, answer: Answer) {
        if (!answer.isEnable){
            viewHolderAnswerCloze.lnParentStart.setOnClickListener {
                onClickListenerAny.onClickListener(
                    answer
                )
            }
            viewHolderAnswerCloze.lnParentEnd.setOnClickListener {
                onClickListenerAny.onClickListener(
                    answer
                )
            }
        }
    }

    private fun initState(viewHolderAnswerCloze: ViewHolderAnswerCloze, answer: Answer) = when {
        answer.isCorrectItem -> initCheckCorrectItem(viewHolderAnswerCloze, answer)
        else -> initStateCheckLevel(viewHolderAnswerCloze, answer)
    }

    override fun getItemCount(): Int = list.size

    private fun initCheckCorrectItem(viewHolderAnswerCloze: ViewHolderAnswerCloze, answer: Answer) {
        when (answer.isSelected) {
            true -> initCheckCorrectItemSelect(viewHolderAnswerCloze, answer)
            false -> correctAnswerItem(viewHolderAnswerCloze)
        }
    }

    private fun initCheckCorrectItemSelect(
        viewHolderAnswerCloze: ViewHolderAnswerCloze,
        answer: Answer
    ) = when (answer.isCorrect) {
        1 -> correctAnswerItem(viewHolderAnswerCloze)
        else -> inCorrectAnswerItem(viewHolderAnswerCloze)
    }

    private fun initStateCheckLevel(viewHolderAnswerCloze: ViewHolderAnswerCloze, answer: Answer) {
        when (answer.isSelected) {
            true -> answerSelectQuestionItem(viewHolderAnswerCloze)
            false -> answerUnSelectQuestionItem(viewHolderAnswerCloze)
        }
    }

    private fun answerUnSelectQuestionItem(viewHolderAnswerCloze: ViewHolderAnswerCloze) {
        viewHolderAnswerCloze.txtTitleStart.setTextColor(context.resources.getColor(R.color.color_grey_text_answer))
        viewHolderAnswerCloze.txtTitleEnd.setTextColor(context.resources.getColor(R.color.color_grey_text_answer))
        viewHolderAnswerCloze.lnParentEnd.setBackgroundResource(R.drawable.rounded_white_shape)
        viewHolderAnswerCloze.lnParentStart.setBackgroundResource(R.drawable.rounded_white_shape)
        viewHolderAnswerCloze.radioButtonStart.isChecked = false
        viewHolderAnswerCloze.radioButtonEnd.isChecked = false
        setCircleColor(
            context,
            viewHolderAnswerCloze.radioButtonStart,
            R.color.color_default_light_radio_button
        )

        setCircleColor(
            context,
            viewHolderAnswerCloze.radioButtonEnd,
            R.color.color_default_light_radio_button
        )
    }

    private fun answerSelectQuestionItem(viewHolderAnswerCloze: ViewHolderAnswerCloze) {
        viewHolderAnswerCloze.txtTitleStart.setTextColor(context.resources.getColor(R.color.black))
        viewHolderAnswerCloze.txtTitleEnd.setTextColor(context.resources.getColor(R.color.black))
        viewHolderAnswerCloze.lnParentStart.setBackgroundResource(R.drawable.rounded_blue_item_answer_cloze_shape)
        viewHolderAnswerCloze.lnParentEnd.setBackgroundResource(R.drawable.rounded_blue_item_answer_cloze_shape)
        viewHolderAnswerCloze.radioButtonStart.isChecked = true
        viewHolderAnswerCloze.radioButtonEnd.isChecked = true
        setCircleColor(
            context,
            viewHolderAnswerCloze.radioButtonStart,
            R.color.color_blue_light_radio_button
        )

        setCircleColor(
            context,
            viewHolderAnswerCloze.radioButtonEnd,
            R.color.color_blue_light_radio_button
        )
    }

    private fun correctAnswerItem(viewHolderAnswerCloze: ViewHolderAnswerCloze) {
        viewHolderAnswerCloze.txtTitleStart.setTextColor(context.resources.getColor(R.color.white))
        viewHolderAnswerCloze.txtTitleEnd.setTextColor(context.resources.getColor(R.color.white))
        viewHolderAnswerCloze.lnParentEnd.setBackgroundResource(R.drawable.rounded_correct_shape)
        viewHolderAnswerCloze.lnParentStart.setBackgroundResource(R.drawable.rounded_correct_shape)
        viewHolderAnswerCloze.radioButtonStart.isChecked = true
        viewHolderAnswerCloze.radioButtonEnd.isChecked = true
        setCircleColor(
            context,
            viewHolderAnswerCloze.radioButtonStart,
            R.color.color_green_light_radio_button
        )

        setCircleColor(
            context,
            viewHolderAnswerCloze.radioButtonEnd,
            R.color.color_green_light_radio_button
        )
    }

    private fun inCorrectAnswerItem(viewHolderAnswerCloze: ViewHolderAnswerCloze) {
        viewHolderAnswerCloze.txtTitleStart.setTextColor(context.resources.getColor(R.color.white))
        viewHolderAnswerCloze.txtTitleEnd.setTextColor(context.resources.getColor(R.color.white))
        viewHolderAnswerCloze.lnParentStart.setBackgroundResource(R.drawable.rounded_in_correct_shape)
        viewHolderAnswerCloze.lnParentEnd.setBackgroundResource(R.drawable.rounded_in_correct_shape)
        viewHolderAnswerCloze.radioButtonStart.isChecked = true
        viewHolderAnswerCloze.radioButtonEnd.isChecked = true
        setCircleColor(
            context,
            viewHolderAnswerCloze.radioButtonStart,
            R.color.color_red_light_radio_button
        )

        setCircleColor(
            context,
            viewHolderAnswerCloze.radioButtonEnd,
            R.color.color_red_light_radio_button
        )
    }

    fun updateList(listAnswer: List<Answer>) {
        list.clear()
        list.addAll(listAnswer)
        notifyDataSetChanged()
    }
}