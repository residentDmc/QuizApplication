package com.vesam.quiz.ui.view.adapter.answer_cloze_list


import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.radiobutton.MaterialRadioButton
import com.vesam.quiz.R

class ViewHolderAnswerCloze(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val lnParentStart = itemView.findViewById(R.id.lnParentStart) as LinearLayout
    val lnParentEnd = itemView.findViewById(R.id.lnParentEnd) as LinearLayout
    val txtTitleEnd = itemView.findViewById(R.id.txtTitleEnd) as TextView
    val txtTitleStart = itemView.findViewById(R.id.txtTitleStart) as TextView
    val radioButtonEnd = itemView.findViewById(R.id.radioButtonEnd) as MaterialRadioButton
    var radioButtonStart = itemView.findViewById(R.id.radioButtonStart) as MaterialRadioButton
}