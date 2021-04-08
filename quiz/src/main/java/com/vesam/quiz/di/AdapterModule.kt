package com.vesam.quiz.di

import com.vesam.quiz.ui.view.adapter.answer_list.AnswerAdapter
import com.vesam.quiz.ui.view.adapter.quiz_list.QuestionAdapter
import org.koin.dsl.module

val adapterModule = module {
    single { QuestionAdapter() }
    single { AnswerAdapter() }
}