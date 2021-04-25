package com.vesam.quiz.ui.view.application

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.vesam.quiz.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppQuiz : MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)

    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        startKoin {
            androidContext(this@AppQuiz)
            modules(listOf(appModule, adapterModule, DatabaseModule, repoModule, viewModelModule))
        }
    }
}