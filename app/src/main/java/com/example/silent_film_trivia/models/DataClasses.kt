package com.example.silent_film_trivia.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Question(
    @Expose @PrimaryKey @SerializedName("_id") val id: String,
    @Expose val prompt: String,
    @Expose val choices: ArrayList<String>,
    @Expose val answer: String,
    @Expose val info: String,
    @Expose var isAnswered: Boolean,
    @Expose var isCorrect: Boolean
) : Parcelable

@Entity
data class Session(
    @Expose val startTime: Long,
    @Expose val questions: ArrayList<Question>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @Expose
    var isInProgress: Boolean = true
}