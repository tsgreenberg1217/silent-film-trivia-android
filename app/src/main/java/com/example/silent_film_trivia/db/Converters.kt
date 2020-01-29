package com.example.silent_film_trivia.db

import androidx.room.Query
import androidx.room.TypeConverter
import com.example.silent_film_trivia.models.Question
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromQuestionArray(questions: Array<Question>): String {
        return Gson().toJson(questions, Question::class.java)
    }

    @TypeConverter
    fun toQuestionArray(json: String): Array<Question> {
        val typeToken = object : TypeToken<ArrayList<Question>>() {}.type
        return Gson().fromJson(json, typeToken)
    }


}