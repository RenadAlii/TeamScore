package com.example.teamscorehw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.teamscorehw.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity() {

    private val viewModel :TeamScoreModel by viewModels()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //set the text for the textView
        setScoreText()

        binding.buttonAdd4.setOnClickListener {
increaseScore(binding.buttonAdd4.id)
            setScoreText()

        }

        binding.buttonAddOne.setOnClickListener {
           increaseScore(binding.buttonAddOne.id)
            setScoreText()

        }

        binding.buttonSubtract2.setOnClickListener {
          if(viewModel.scoreCheck()){
increaseScore(binding.buttonSubtract2.id)
              setScoreText()


          }else{
              showWarningDialog()
          }

        }


    }


    private fun increaseScore(buttonID: Int){

        when(buttonID) {
            binding.buttonAddOne.id -> viewModel.addOneToScore()
            binding.buttonAdd4.id -> viewModel.addFourToScore()
            else -> viewModel.subTwoFromScore()
        }

    }
    private fun showWarningDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.negativeScore))
            .setMessage(getString(R.string.warning))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)) { _, _ ->
                exitScoreCount()
            }
            .setPositiveButton(getString(R.string.start_again)) { _, _ ->
                restartScoreCount()
            }
            .show()
    }


    //Exits the App.
    private fun exitScoreCount() = this.finish()


   // Re-initializes the data in the ViewModel and updates the views with the new data, to
        // restart the App.
    private fun restartScoreCount() {
       viewModel.reinitializeData()
       setScoreText()

   }

    //set the textView with the new Score
    private fun setScoreText()  {
        binding.textViewScoreCount.text = getString(R.string.score, viewModel.score)
    }


}