package com.vesam.quiz.di

import com.vesam.quiz.ui.view.adapter.answer_list.AnswerAdapter
import com.vesam.quiz.ui.view.adapter.answer_result_list.AnswerResultAdapter
import org.koin.dsl.module

val adapterModule = module {
    single { AnswerAdapter() }
    single { AnswerResultAdapter(get()) }
}