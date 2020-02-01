package com.example.silent_film_trivia.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.silent_film_trivia.models.Question
import com.example.silent_film_trivia.models.Session

@Dao
interface SessionDao : BaseDao<Session> {
    @Query("select * from session")
    suspend fun getSessions(): List<Session>

    @Query("select * from session where id == :id")
    suspend fun getSession(id: Long): Session

    @Query("update session set questions = :questions where id == :id")
    suspend fun updateQuestions(id: Long, questions: ArrayList<Question>)
}