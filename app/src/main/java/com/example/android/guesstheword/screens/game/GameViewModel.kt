package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    // The current word
    var word = MutableLiveData<String>()

    // The current score
    var score = MutableLiveData<Int>()
    private lateinit var wordList: MutableList<String>
    init {
        score.value = 0
            Log.i("GameViewModel", "ViewModel created")
        resetList()
        nextWord()

        }
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("Viewmodel", "ViewModel destroyed")
    }
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            //gameFinished()
        } else {
            word.value = wordList.removeAt(0)
        }

    }
    fun onSkip() {
        score.value = (score.value)?.minus(1)
        nextWord()
    }

     fun onCorrect() {
        score.value = (score.value)?.plus(1)
        nextWord()
    }
}
