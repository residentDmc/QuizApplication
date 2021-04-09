package com.vesam.quiz.data.database

import androidx.room.*
import com.vesam.quiz.data.model.quiz_detail.ResponseQuizDetailModel

@Dao
interface DetailsDAO {

    @Query("select * from details_entity WHERE id IN(:id)")
    suspend fun getDetails(id: Int): ResponseQuizDetailModel?

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(Details: ResponseQuizDetailModel)

    @Update
    suspend fun updateDetails(Details: ResponseQuizDetailModel)

    @Query("DELETE FROM details_entity")
    suspend fun delete()
}