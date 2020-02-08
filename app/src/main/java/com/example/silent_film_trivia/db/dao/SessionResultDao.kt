package com.example.silent_film_trivia.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.silent_film_trivia.SilentFilmTriviaApplication
import com.example.silent_film_trivia.models.Question
import com.example.silent_film_trivia.models.Session
import com.example.silent_film_trivia.models.SessionResult

@Dao
interface SessionResultDao : BaseDao<SessionResult> {

    @Transaction
    suspend fun insertResultAndDeleteSession(sessionId: Long, sessionResult: SessionResult) {
        SilentFilmTriviaApplication.database.also {
            it.sessionDao().deleteSession(sessionId)
            it.sessionResultDao().insert(sessionResult)
        }
    }
}