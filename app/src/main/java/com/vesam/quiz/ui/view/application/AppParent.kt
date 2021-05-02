package com.vesam.quiz.ui.view.application

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.developer.crashx.config.CrashConfig
import com.vesam.quiz.utils.application.AppQuiz

class AppParent : MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)

    }

    override fun onCreate() {
        super.onCreate()
        CrashConfig.Builder.create().apply()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        AppQuiz().init(this)
    }
}