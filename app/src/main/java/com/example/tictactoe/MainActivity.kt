package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    private lateinit var buttonCircle: ImageButton
    private lateinit var buttonCross: ImageButton

    private fun init(){
        buttonCircle = findViewById(R.id.btnCircle)
        buttonCross = findViewById(R.id.btnCross)
    }

    fun btnGameActivity(view:View){
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("figure", view.tag.toString())
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }
}