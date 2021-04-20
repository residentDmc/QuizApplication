package com.vesam.quiz.utils.extention

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.vesam.quiz.R
import com.vesam.quiz.interfaces.OnClickListener
import com.vesam.quiz.interfaces.OnClickListenerAny
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun timeDownProgressBar(progressBar: ProgressBar, time: Int,onClickListener: OnClickListener) {
    var differentTime = time.toLong() * 8
    progressBar.max = differentTime.toInt()
    progressBar.progress = differentTime.toInt()
    Observable.intervalRange(0, differentTime, 0, 50, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnComplete(onClickListener::onClickListener)
        .subscribe {
            differentTime--
            progressBar.progress = (differentTime).toInt()
        }
}