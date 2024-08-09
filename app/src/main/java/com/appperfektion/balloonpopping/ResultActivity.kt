package com.appperfektion.balloonpopping

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appperfektion.balloonpopping.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    lateinit var resultBinding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(resultBinding.root)

    }
}