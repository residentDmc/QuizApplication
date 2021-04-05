package com.vesam.quiz.di

import com.vesam.quiz.ui.viewmodel.QuizViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { QuizViewModel(get(),get(),get()) }
}