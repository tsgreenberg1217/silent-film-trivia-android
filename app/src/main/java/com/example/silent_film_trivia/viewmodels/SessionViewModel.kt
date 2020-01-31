package com.example.silent_film_trivia.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.silent_film_trivia.db.SilentFilmTriviaDatabase
import com.example.silent_film_trivia.models.Question
import com.example.silent_film_trivia.models.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SessionViewModel : ViewModel() {
    var sessionId: Long? = null
    val questions: MutableLiveData<ArrayList<Question>> by lazy {
        MutableLiveData<ArrayList<Question>>().also {
            sessionId?.let { sId ->
                viewModelScope.launch(Dispatchers.IO) {
                    val session: Session =
                        SilentFilmTriviaDatabase.INSTANCE!!.sessionDao().getSession(sId)
                    launch(Dispatchers.Main) {
                        questions.value = session.questions
                    }
                }
            }
        }
    }

}