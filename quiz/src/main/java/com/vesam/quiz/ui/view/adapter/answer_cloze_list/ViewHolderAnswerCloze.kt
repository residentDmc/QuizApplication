package com.vesam.quiz.ui.view.adapter.answer_cloze_list


import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.vesam.quiz.R

class ViewHolderAnswerCloze(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val lnParent = itemView.findViewById(R.id.lnParent) as LinearLayoutCompat
    val txtTitle = itemView.findViewById(R.id.txtTitle) as TextView
}