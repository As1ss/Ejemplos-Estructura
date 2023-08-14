package com.example.empty_proyect

import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaActionSound
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mediaPlayer = MediaPlayer.create(this,R.raw.sound)

        val ejemploLabel:TextView = findViewById(R.id.ejemploLabel)
        var contador : Int = 1

        ejemploLabel.apply {
            this.text = "Pulsa aquí"
            setTextColor(Color.YELLOW)
            setBackgroundColor(Color.BLACK)
            setTypeface(Typeface.MONOSPACE,Typeface.BOLD)
            setTextSize(3,35f)
            textAlignment = View.TEXT_ALIGNMENT_CENTER
            this.setTextColor(Color.YELLOW)
        }
        ejemploLabel.setOnClickListener{
            mediaPlayer.start()
            Toast.makeText(this,"Has eurptadolf ${contador++} veces.",Toast.LENGTH_SHORT).show()
        }

    }
}