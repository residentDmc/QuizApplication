package com.vesam.quiz.ui.view.adapter.quiz_list


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vesam.quiz.R

class ViewHolderQuiz(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val txtTitle = itemView.findViewById(R.id.txtTitle) as TextView
    val txtNumber = itemView.findViewById(R.id.txtNumber) as TextView
    val lnParent = itemView.findViewById(R.id.lnParent) as View
}