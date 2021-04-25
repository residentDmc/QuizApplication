package com.vesam.quiz.utils.application

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.vesam.quiz.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppQuiz : MultiDexApplication() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        @SuppressLint("StaticFieldLeak")
        lateinit var activity: Activity
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)

    }

    fun init(context: Context) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        AppQuiz.context = context
        startKoin {
            androidContext(context)
            modules(listOf(appModule, adapterModule, DatabaseModule, repoModule, viewModelModule))
        }
    }
}