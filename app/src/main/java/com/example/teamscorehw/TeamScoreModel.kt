package com.example.teamscorehw

import androidx.lifecycle.ViewModel

class TeamScoreModel: ViewModel(){


    private var _score = 0
    val score: Int
        get() = _score










    // fun to Check if the score >= 0
     fun scoreCheck(): Boolean{
        return _score > 0 && _score-2 >= 0

    }

    // fun to add 1 to the score
     fun addOneToScore(){
            _score++
        }


    // fun to add 4 to the score
     fun addFourToScore(){
        _score += 4
    }

    // fun to subtract 2 from the score
     fun subTwoFromScore(){
        _score -= 2
    }
    /*
* Re-initializes the game data to restart the game.
*/
    fun reinitializeData() {
        _score = 0

    }

}
