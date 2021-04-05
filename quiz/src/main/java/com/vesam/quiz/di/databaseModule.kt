package com.vesam.quiz.di

import com.vesam.quiz.data.database.QuizDatabase
import org.koin.dsl.module

val DatabaseModule = module {

    single { QuizDatabase.getInstance(get()) }
}