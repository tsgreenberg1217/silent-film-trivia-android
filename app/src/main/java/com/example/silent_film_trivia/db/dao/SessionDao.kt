package com.example.silent_film_trivia.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.silent_film_trivia.models.Session

@Dao
interface SessionDao : BaseDao<Session> {
    @Query("select * from session")
    suspend fun getQuestions(): List<Session>
}