package com.vesam.quiz.utils.application

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.vesam.quiz.di.appModule
import com.vesam.quiz.di.DatabaseModule
import com.vesam.quiz.di.adapterModule
import com.vesam.quiz.di.repoModule
import com.vesam.quiz.di.viewModelModule
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

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        context = applicationContext
        startKoin {
            androidContext(this@AppQuiz)
            modules(listOf(appModule, adapterModule, DatabaseModule, repoModule, viewModelModule))
        }
    }
}