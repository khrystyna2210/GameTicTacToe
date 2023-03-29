package com.example.tictactoe

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.Arrays

class GameActivity : AppCompatActivity() {

    // 0: circle, 1: cross, 2: empty

    private lateinit var textComment: TextView
    private lateinit var buttonPlayAgain : Button
    private lateinit var layout: androidx.gridlayout.widget.GridLayout

    private var gameActive = true
    private var activePlayer = 0

    private val gameState = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    private val winningPositions: Array<IntArray> = arrayOf(
        intArrayOf(0, 1 ,2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7 ,8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4 ,7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4 ,8),
        intArrayOf(2, 4, 6)
    )

    private fun init(){
        textComment = findViewById(R.id.tvComment)
        buttonPlayAgain = findViewById(R.id.btnPlayAgain)
        layout = findViewById(R.id.gridlayout)
    }
    fun dropIn(view : View){
        var tappedCounter=Integer.parseInt(view.tag.toString())
        var counter = view as ImageView
        if(gameState[tappedCounter]==2 && gameActive){
            gameState[tappedCounter]=activePlayer
            if(activePlayer==0){
                counter.setImageResource(R.drawable.circle);
                activePlayer =1;
            }else if(activePlayer==1){
                counter.setImageResource(R.drawable.cross);
                activePlayer = 0;
            }
        }
        if(!gameState.contains(2)){
            textComment.text = "We have no winner. Try again!"
            buttonPlayAgain.visibility = View.VISIBLE
            textComment.visibility = View.VISIBLE
        }

        for (winningPosition: IntArray in winningPositions) {

            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                var winner = ""
                if (activePlayer == 1) {
                    winner = "Circle"
                } else {
                    winner = "Cross"
                }
                gameActive = false

                textComment.text = winner + " has won!"
                buttonPlayAgain.visibility = View.VISIBLE
                textComment.visibility = View.VISIBLE
            }
        }


    }

    fun playAgain(view: View) {

        buttonPlayAgain.visibility = View.INVISIBLE
        textComment.visibility = View.INVISIBLE
        for (i in 0..8){
            var counter: ImageView = layout.getChildAt(i) as ImageView
            counter.setImageDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        gameState.fill(2)
        activePlayer = 0
        gameActive = true

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        init()
        val figure:String = intent.getStringExtra("figure").toString()
        if(figure == "circle"){
            activePlayer = 0
        }else activePlayer = 1

    }
}