package com.vesam.quiz.utils.tools

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vesam.quiz.R

class GlideTools(private val context: Context, private val handelErrorTools: HandelErrorTools) {

    fun displayImageOriginal(img: ImageView?, url: String?) {
        try {
            val options: RequestOptions = RequestOptions()
                .placeholder(R.drawable.ic_load_pic)
            img?.let {
                Glide.with(context).load(url)
                    .apply(options)
                    .into(it)
            }
        } catch (e: Exception) {
            handelErrorTools.handelError(e)
        }
    }
}