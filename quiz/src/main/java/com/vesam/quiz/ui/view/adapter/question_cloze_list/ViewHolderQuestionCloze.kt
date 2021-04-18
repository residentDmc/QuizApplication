package com.vesam.quiz.ui.view.adapter.question_cloze_list


import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.vesam.quiz.R

class ViewHolderQuestionCloze(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    val lnTitle = itemView.findViewById(R.id.lnTitle) as LinearLayoutCompat
    val txtTitle = itemView.findViewById(R.id.txtTitle) as TextView
    val rcAnswerCloze = itemView.findViewById(R.id.rcAnswerCloze) as RecyclerView

}