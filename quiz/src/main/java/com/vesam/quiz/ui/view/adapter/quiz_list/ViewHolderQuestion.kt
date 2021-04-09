package com.vesam.quiz.ui.view.adapter.quiz_list


import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vesam.quiz.R

class ViewHolderQuestion(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val txtTitle = itemView.findViewById(R.id.txtTitle) as TextView
    val lnParent = itemView.findViewById(R.id.lnParent) as LinearLayout
}