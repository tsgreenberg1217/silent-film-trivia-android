package com.tsgreenberg.silent_film_trivia.models

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
    @Expose @SerializedName("giphy_id") val giphyId: String,
    @Expose val prompt: String,
    @Expose val choices: MutableList<String>,
    @Expose val answer: String,
    @Expose val info: String,
    var isAnswered: Boolean,
    var isCorrect: Boolean
) : Parcelable

@Entity
data class Session(
    @Expose val startTime: Long,
    @Expose val questions: MutableList<Question>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @Expose
    var isInProgress: Boolean = true

}

@Entity
data class SessionResult(val questions: MutableList<Question>) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @Expose
    var correctAnswers: Int = 0
    @Expose
    var questionsAsked: Int = 0

    init {
        questionsAsked = questions.size
        correctAnswers = questions.filter { q -> q.isCorrect }.size

    }
}

