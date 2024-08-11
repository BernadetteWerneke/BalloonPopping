package com.appperfektion.balloonpopping

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.appperfektion.balloonpopping.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        object : CountDownTimer(5000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                mainBinding.textViewTimer.text = (millisUntilFinished / 1000).toString()     //time in seconds, type long
            }

            override fun onFinish() {
                mainBinding.textViewTimer.visibility = View.INVISIBLE
                mainBinding.textViewRemainingTime.visibility = View.VISIBLE
                mainBinding.textViewScore.visibility = View.VISIBLE
                mainBinding.gridLayout.visibility = View.VISIBLE

                object : CountDownTimer(30000, 1000){
                    override fun onTick(millisUntilFinished: Long) {
                        mainBinding.textViewRemainingTime.text = "Remaining Time: ${millisUntilFinished/1000}"
                    }

                    override fun onFinish() {
                        val intent = Intent(this@MainActivity,ResultActivity::class.java)
                        startActivity(intent)
                    }
                }.start()
            }

        }.start()
    }

    fun increaseScoreByOne(view: View){
        score++
        mainBinding.textViewScore.text = "Score: $score"
    }
}