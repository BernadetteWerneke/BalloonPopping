package com.appperfektion.balloonpopping

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.appperfektion.balloonpopping.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    var score = 0
    lateinit var handler: Handler
    lateinit var runnable: Runnable
    lateinit var balloonsArray: Array<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        balloonsArray = arrayOf(
            mainBinding.balloon1,
            mainBinding.balloon2,
            mainBinding.balloon3,
            mainBinding.balloon4,
            mainBinding.balloon5,
            mainBinding.balloon6,
            mainBinding.balloon7,
            mainBinding.balloon8,
            mainBinding.balloon9
        )

        object : CountDownTimer(5000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                mainBinding.textViewTimer.text = (millisUntilFinished / 1000).toString()     //time in seconds, type long
            }

            override fun onFinish() {

                balloonControl()

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

    private fun balloonControl() {
        mainBinding.textViewTimer.visibility = View.INVISIBLE
        mainBinding.textViewRemainingTime.visibility = View.VISIBLE
        mainBinding.textViewScore.visibility = View.VISIBLE

        handler = Handler(Looper.getMainLooper())
        runnable = Runnable{

            for (balloon in balloonsArray){
                   balloon.visibility =  View.INVISIBLE
            }
            mainBinding.gridLayout.visibility = View.VISIBLE
            val i = Random.nextInt(balloonsArray.size)
            balloonsArray[i].visibility = View.VISIBLE

            handler.postDelayed(runnable, 2000)
        }
        handler.post(runnable)
    }

    fun increaseScoreByOne(view: View){
        score++
        mainBinding.textViewScore.text = "Score: $score"
    }
}