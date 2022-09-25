package com.example.android.guesstheword.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore : Int): ViewModel() {

    //eventPlayAgain
    private val _eventPlayAgain = MutableLiveData<Boolean>()
     val eventPlayAgain : LiveData<Boolean>
            get() = _eventPlayAgain

    private val _currentScore = MutableLiveData<Int>()
        val currentScore: LiveData<Int>
            get() = _currentScore

    init {
        _currentScore.value = finalScore

    }

    fun onPlayAgain()
    {
        _eventPlayAgain.value = true
    }

     fun onPlayAgainComplete()
    {
        _eventPlayAgain.value = false
    }

}