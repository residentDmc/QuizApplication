package com.vesam.quiz.ui.view.adapter.answer_quiz_result_list


import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vesam.quiz.R

class ViewHolderAnswerResult(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val lnParent = itemView.findViewById(R.id.lnParent) as LinearLayout
    val txtTitle = itemView.findViewById(R.id.txtTitle) as TextView
    val imgSuccess = itemView.findViewById(R.id.imgSuccess) as View
    val imgUnSuccess = itemView.findViewById(R.id.imgUnSuccess) as View
}