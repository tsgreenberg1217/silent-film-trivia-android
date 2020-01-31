package com.example.silent_film_trivia.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.silent_film_trivia.db.dao.SessionDao
import com.example.silent_film_trivia.models.Session

@Database(entities = [Session::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SilentFilmTriviaDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
}