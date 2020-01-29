package com.example.silent_film_trivia.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Question(
    @Expose @PrimaryKey @SerializedName("_id") val id: String,
    @Expose val prompt: String,
    @Expose val choices: Array<String>,
    @Expose val answer: String,
    @Expose val info: String,
    @Expose var isAnswering: Boolean,
    @Expose var isCorrect: Boolean
)

@Entity
data class Session(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @Expose val startTime: Long,
    @Expose val questions: Array<Question>
)