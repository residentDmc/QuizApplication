package com.vesam.quiz.ui.view.adapter.answer_quiz_list

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.vesam.quiz.R
import com.vesam.quiz.data.model.quiz_detail.Answer
import com.vesam.quiz.interfaces.OnClickListenerAny
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.HOW_DISPLAY_CORRECT_ANSWER
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.STEP_BY_STEP
import com.vesam.quiz.utils.extention.checkPersianCharacter
import com.vesam.quiz.utils.extention.setCircleColor
import java.util.*

class AnswerAdapter(private val context: Context) : RecyclerView.Adapter<ViewHolderAnswer>() {

    lateinit var onClickListenerAny: OnClickListenerAny
    private val list: ArrayList<Answer> = ArrayList()

    fun setOnItemClickListener(onClickListenerAny: OnClickListenerAny) {
        this.onClickListenerAny = onClickListenerAny
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAnswer =
        ViewHolderAnswer(getViewHolder(parent))

    private fun getViewHolder(parent: ViewGroup): View = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_answer_quiz, parent, false)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolderAnswer: ViewHolderAnswer, position: Int) {
        val answer = list[position]
        checkPersianCharacter(
            answer.title,
            viewHolderAnswer.lnParentStart,
            viewHolderAnswer.lnParentEnd
        )
        initType(viewHolderAnswer, answer)
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

    private fun initType(viewHolderAnswer: ViewHolderAnswer, answer: Answer) =
        when (HOW_DISPLAY_CORRECT_ANSWER) {
            STEP_BY_STEP -> initStateCorrect(viewHolderAnswer, answer)
            else -> initStateCheckLevel(viewHolderAnswer, answer)
        }

    private fun initStateCheckLevel(viewHolderAnswer: ViewHolderAnswer, answer: Answer) {
        when (answer.isCheckLevel) {
            0 -> initDefaultCheckLevel(viewHolderAnswer)
            1 -> initCheckLevel(viewHolderAnswer)
        }
    }

    private fun initDefaultCheckLevel(viewHolderAnswer: ViewHolderAnswer) {
        viewHolderAnswer.imgBtnSuccessStart.visibility = View.GONE
        viewHolderAnswer.imgBtnUnSuccessStart.visibility = View.GONE
        viewHolderAnswer.radioButtonStart.isChecked = false
        setCircleColor(
            context,
            viewHolderAnswer.radioButtonStart,
            R.color.color_default_radio_button
        )
        viewHolderAnswer.lnParentStart.setBackgroundResource(R.drawable.rounded_white_shape)


        viewHolderAnswer.imgBtnSuccessEnd.visibility = View.GONE
        viewHolderAnswer.imgBtnUnSuccessEnd.visibility = View.GONE
        viewHolderAnswer.radioButtonEnd.isChecked = false
        setCircleColor(
            context,
            viewHolderAnswer.radioButtonEnd,
            R.color.color_default_radio_button
        )
        viewHolderAnswer.lnParentEnd.setBackgroundResource(R.drawable.rounded_white_shape)

    }

    private fun initCheckLevel(viewHolderAnswer: ViewHolderAnswer) {
        viewHolderAnswer.imgBtnSuccessStart.visibility = View.GONE
        viewHolderAnswer.imgBtnUnSuccessStart.visibility = View.GONE
        viewHolderAnswer.radioButtonStart.isChecked = true
        setCircleColor(
            context,
            viewHolderAnswer.radioButtonStart,
            R.color.color_blue_radio_button
        )
        viewHolderAnswer.lnParentStart.setBackgroundResource(R.drawable.rounded_blue_shape)


        viewHolderAnswer.imgBtnSuccessEnd.visibility = View.GONE
        viewHolderAnswer.imgBtnUnSuccessEnd.visibility = View.GONE
        viewHolderAnswer.radioButtonEnd.isChecked = true
        setCircleColor(
            context,
            viewHolderAnswer.radioButtonEnd,
            R.color.color_default_radio_button
        )
        viewHolderAnswer.lnParentEnd.setBackgroundResource(R.drawable.rounded_blue_shape)

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
        viewHolderAnswer.radioButtonStart.isChecked = false
        setCircleColor(
            context,
            viewHolderAnswer.radioButtonStart,
            R.color.color_default_radio_button
        )
        viewHolderAnswer.lnParentStart.setBackgroundResource(R.drawable.rounded_white_shape)


        viewHolderAnswer.imgBtnSuccessEnd.visibility = View.GONE
        viewHolderAnswer.imgBtnUnSuccessEnd.visibility = View.GONE
        viewHolderAnswer.radioButtonEnd.isChecked = false
        setCircleColor(
            context,
            viewHolderAnswer.radioButtonEnd,
            R.color.color_default_radio_button
        )
        viewHolderAnswer.lnParentEnd.setBackgroundResource(R.drawable.rounded_white_shape)

    }

    private fun initSuccess(viewHolderAnswer: ViewHolderAnswer) {
        viewHolderAnswer.imgBtnSuccessStart.visibility = View.VISIBLE
        viewHolderAnswer.imgBtnUnSuccessStart.visibility = View.GONE
        viewHolderAnswer.radioButtonStart.isChecked = true
        setCircleColor(
            context,
            viewHolderAnswer.radioButtonStart,
            R.color.color_green_radio_button
        )
        viewHolderAnswer.lnParentStart.setBackgroundResource(R.drawable.rounded_green_shape)

        viewHolderAnswer.imgBtnSuccessEnd.visibility = View.VISIBLE
        viewHolderAnswer.imgBtnUnSuccessEnd.visibility = View.GONE
        viewHolderAnswer.radioButtonEnd.isChecked = true
        setCircleColor(
            context,
            viewHolderAnswer.radioButtonEnd,
            R.color.color_green_radio_button
        )
        viewHolderAnswer.lnParentEnd.setBackgroundResource(R.drawable.rounded_green_shape)
    }



    private fun initUnSuccess(viewHolderAnswer: ViewHolderAnswer) {
        viewHolderAnswer.imgBtnSuccessStart.visibility = View.GONE
        viewHolderAnswer.imgBtnUnSuccessStart.visibility = View.VISIBLE
        viewHolderAnswer.radioButtonStart.isChecked = true
        setCircleColor(
            context,
            viewHolderAnswer.radioButtonStart,
            R.color.color_red_radio_button
        )
        viewHolderAnswer.lnParentStart.setBackgroundResource(R.drawable.rounded_red_shape)

        viewHolderAnswer.imgBtnSuccessEnd.visibility = View.GONE
        viewHolderAnswer.imgBtnUnSuccessEnd.visibility = View.VISIBLE
        viewHolderAnswer.radioButtonEnd.isChecked = true
        setCircleColor(
            context,
            viewHolderAnswer.radioButtonEnd,
            R.color.color_red_radio_button
        )
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

    fun initFindIsCorrectAnswer(): Answer? {
        list.forEach {
            when (it.isCorrect) {
                1 -> return it
            }
        }
        return null
    }

    fun disableClick() {
        list.forEach { it.isEnable = true }
        notifyDataSetChanged()
    }

    fun answerCheckLevel(answer: Answer) {
        val indexOf = list.indexOf(answer)
        answer.isCheckLevel = 1
        list[indexOf] = answer
        list.forEach { it.isEnable = true }
        notifyDataSetChanged()
    }
}