package com.example.silent_film_trivia.db

import androidx.room.Query
import androidx.room.TypeConverter
import com.example.silent_film_trivia.models.Question
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromQuestionArray(questions: ArrayList<Question>): String {
        return Gson().toJson(questions)
    }

    @TypeConverter
    fun toQuestionArray(json: String): ArrayList<Question> {
        val typeToken = object : TypeToken<ArrayList<Question>>() {}.type
        return Gson().fromJson(json, typeToken)
    }


}