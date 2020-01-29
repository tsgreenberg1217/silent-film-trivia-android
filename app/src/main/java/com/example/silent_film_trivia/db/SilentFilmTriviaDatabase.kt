package com.example.silent_film_trivia.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.silent_film_trivia.db.dao.SessionDao
import com.example.silent_film_trivia.models.Session

@Database(entities = [Session::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SilentFilmTriviaDatabase : RoomDatabase() {

    abstract fun sessionDao(): SessionDao

    companion object {
        var INSTANCE: SilentFilmTriviaDatabase? = null

        fun getDbInstance(context: Context): SilentFilmTriviaDatabase {
            return INSTANCE ?: setAndReturnDbInstance(context)
        }

        fun setAndReturnDbInstance(context: Context):SilentFilmTriviaDatabase{
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                SilentFilmTriviaDatabase::class.java,
                "silent-film-trivia-db"
            ).build()
            return INSTANCE as SilentFilmTriviaDatabase
        }

    }
}