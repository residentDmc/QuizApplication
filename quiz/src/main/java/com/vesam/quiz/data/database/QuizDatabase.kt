package com.vesam.quiz.data.database

import android.content.Context
import androidx.room.*
import com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel
import com.vesam.quiz.data.model.quiz_list.Quiz
import com.vesam.quiz.utils.build_config.BuildConfig.Companion.QUIZ_DATABASE
import com.vesam.quiz.utils.converter.DetailsConverter
import com.vesam.quiz.utils.converter.ListQuestionConverter

@Database(entities = [Quiz::class, ResponseQuizDetailModel::class], version = 1, exportSchema = false)
@TypeConverters(ListQuestionConverter::class,DetailsConverter::class)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun quizDAO(): QuizDAO
    abstract fun detailsDAO(): DetailsDAO

    companion object {
        private var instance: QuizDatabase? = null
        fun getInstance(context: Context): QuizDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    QuizDatabase::class.java,
                    QUIZ_DATABASE)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}