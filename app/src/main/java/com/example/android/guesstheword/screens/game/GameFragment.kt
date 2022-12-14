/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.database.DatabaseUtils
import android.os.*
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders.of
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding
import java.util.List.of
import java.util.Optional.of

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {




    // The list of words - the front of the list is the next word to guess

    // Bindings
    private lateinit var binding: GameFragmentBinding

    private lateinit var viewModel: GameViewModel




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )


        //ViewModelProvider
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        //Observers


        viewModel.eventGameFinished.observe(viewLifecycleOwner, Observer { hasFinished->
            if (hasFinished)
            {
                gameFinished()
                viewModel.onGameFinished()
            }
        })

        viewModel.eventBuzz.observe(viewLifecycleOwner, Observer { eventBuzz->
            if (eventBuzz != GameViewModel.BuzzType.NO_BUZZ)
            {
                buzz(eventBuzz.pattern)
                viewModel.onBuzzComplete()
            }
        })





        //Binding gameViewModel in game_fragment.xml
        binding.gameViewModel = viewModel

        binding.lifecycleOwner = this



        return binding.root



    }

    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()

        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }

    /**
     * Resets the list of words and randomizes the order
     */

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val currentScore = viewModel.score.value ?: 0
        val action = GameFragmentDirections.actionGameToScore(currentScore)
        findNavController(this).navigate(action)
    }


    /**
     * Moves to the next word in the list
     */


    /** Methods for buttons presses **/



    /** Methods for updating the UI **/


}
