package com.tsgreenberg.silent_film_trivia.db

import androidx.room.TypeConverter
import com.tsgreenberg.silent_film_trivia.models.Question
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromQuestionArray(questions: MutableList<Question>): String {
        return Gson().toJson(questions)
    }

    @TypeConverter
    fun toQuestionArray(json: String): MutableList<Question> {
        val typeToken = object : TypeToken<MutableList<Question>>() {}.type
        return Gson().fromJson(json, typeToken)
    }


}