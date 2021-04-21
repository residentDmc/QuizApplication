package com.vesam.quiz.ui.view.adapter.answer_quiz_list


import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.radiobutton.MaterialRadioButton
import com.vesam.quiz.R

class ViewHolderAnswer(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val txtTitleStart = itemView.findViewById(R.id.txtTitleStart) as TextView
    val imgBtnUnSuccessStart = itemView.findViewById(R.id.imgBtnUnSuccessStart) as View
    val imgBtnSuccessStart = itemView.findViewById(R.id.imgBtnSuccessStart) as View
    var radioButtonStart = itemView.findViewById(R.id.radioButtonStart) as MaterialRadioButton
    val lnParentStart = itemView.findViewById(R.id.lnParentStart) as LinearLayout

    val txtTitleEnd = itemView.findViewById(R.id.txtTitleEnd) as TextView
    val imgBtnUnSuccessEnd = itemView.findViewById(R.id.imgBtnUnSuccessEnd) as View
    val imgBtnSuccessEnd = itemView.findViewById(R.id.imgBtnSuccessEnd) as View
    var radioButtonEnd = itemView.findViewById(R.id.radioButtonEnd) as MaterialRadioButton
    val lnParentEnd = itemView.findViewById(R.id.lnParentEnd) as LinearLayout
}