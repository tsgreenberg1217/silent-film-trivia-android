package com.example.silent_film_trivia.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.silent_film_trivia.models.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SessionViewModel : ViewModel() {
    val questions: MutableLiveData<ArrayList<Question>> by lazy {
        MutableLiveData<ArrayList<Question>>().also {
            viewModelScope.launch(Dispatchers.IO) {

            }
        }
    }

}