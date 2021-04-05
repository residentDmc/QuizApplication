package com.vesam.quiz.data.database

import androidx.room.*
import com.vesam.quiz.data.model.quiz_list.Quiz

@Dao
interface QuizDAO {

    @Query("select * from GET_QUIZ_LIST_ENTITY")
    suspend fun getQuiz(): Quiz?

    @Query("select * from GET_QUIZ_LIST_ENTITY")
    suspend fun getListQuiz(): List<Quiz>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quiz: Quiz)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListQuiz(listQuiz: List<Quiz>)

    @Update
    suspend fun updateQuiz(quiz: Quiz)

    @Query("DELETE FROM GET_QUIZ_LIST_ENTITY")
    suspend fun delete()
}