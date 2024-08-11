package com.appperfektion.balloonpopping

import android.content.Intent
import android.media.MediaPlayer
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
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.balloon_pop)

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
                balloon.setImageResource(R.drawable.balloon)
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

        if (mediaPlayer.isPlaying){
            mediaPlayer.seekTo(0)
            mediaPlayer.start()
        } else {
            mediaPlayer.start()
        }

        when(view.id){
            mainBinding.balloon1.id -> mainBinding.balloon1.setImageResource(R.drawable.boom)
            mainBinding.balloon2.id -> mainBinding.balloon2.setImageResource(R.drawable.boom)
            mainBinding.balloon3.id -> mainBinding.balloon3.setImageResource(R.drawable.boom)
            mainBinding.balloon4.id -> mainBinding.balloon4.setImageResource(R.drawable.boom)
            mainBinding.balloon5.id -> mainBinding.balloon5.setImageResource(R.drawable.boom)
            mainBinding.balloon6.id -> mainBinding.balloon6.setImageResource(R.drawable.boom)
            mainBinding.balloon7.id -> mainBinding.balloon7.setImageResource(R.drawable.boom)
            mainBinding.balloon8.id -> mainBinding.balloon8.setImageResource(R.drawable.boom)
            mainBinding.balloon9.id -> mainBinding.balloon9.setImageResource(R.drawable.boom)

        }
    }
}