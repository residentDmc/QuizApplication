package com.vesam.quiz.di

import com.vesam.quiz.ui.view.adapter.quiz_list.QuizAdapter
import org.koin.dsl.module

val adapterModule = module {
    single { QuizAdapter() }
}