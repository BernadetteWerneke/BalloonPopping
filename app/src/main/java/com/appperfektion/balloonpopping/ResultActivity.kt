package com.appperfektion.balloonpopping

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appperfektion.balloonpopping.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    lateinit var resultBinding: ActivityResultBinding
    lateinit var sharedPreferences: SharedPreferences //to store score

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(resultBinding.root)

        val myScore = intent.getIntExtra("score", 0)
        resultBinding.textViewMyScore.text = "Your Score: $myScore"

        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE)
        val highestScore = sharedPreferences.getInt("highestScore", 0)

            if (myScore >= highestScore){
                sharedPreferences.edit().putInt("highestScore", myScore).apply()        //save the score
                resultBinding.textViewHighestScore.text = "Highest Score: $myScore"
            } else {
                resultBinding.textViewHighestScore.text = "Highest Score: $highestScore"
            }
    }
}