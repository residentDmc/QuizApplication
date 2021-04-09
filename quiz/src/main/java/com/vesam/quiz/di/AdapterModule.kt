package com.vesam.quiz.di

import com.vesam.quiz.ui.view.adapter.answer_list.AnswerAdapter
import org.koin.dsl.module

val adapterModule = module {
    single { AnswerAdapter() }
}