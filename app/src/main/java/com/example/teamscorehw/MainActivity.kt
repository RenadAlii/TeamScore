package com.example.teamscorehw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.teamscorehw.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity() {

    private val viewModel :TeamScoreModel by viewModels()

    private lateinit var binding : ActivityMainBinding
    private  var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        count+=viewModel.score
        binding.textViewScoreCount.text = getString(R.string.score,count)

        //set the text for the textView


        binding.buttonAdd4.setOnClickListener {
            count =  setTheScore(2)
            binding.textViewScoreCount.text = getString(R.string.score,count)

        }

        binding.buttonAddOne.setOnClickListener {
            count = setTheScore(1)
            binding.textViewScoreCount.text = getString(R.string.score,count)

        }

        binding.buttonSubtract2.setOnClickListener {
          if(viewModel.scoreCheck()){
              count = setTheScore(3)
              binding.textViewScoreCount.text = getString(R.string.score,count)


          }else{
              showWarningDialog()
          }

        }


    }


    private fun setTheScore(theFunNumber: Int):Int{

        when(theFunNumber){
            1->  viewModel.addOneToScore()
            2 ->  viewModel.addFourToScore()
            else ->  viewModel.subTwoFromScore()
        }
        return  viewModel.score

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
    private fun exitScoreCount() {
        this.finish()
    }

   // Re-initializes the data in the ViewModel and updates the views with the new data, to
        // restart the App.

    private fun restartScoreCount() {

        viewModel.reinitializeData()
        count = viewModel.score
        binding.textViewScoreCount.text = getString(R.string.score,count)


    }

}