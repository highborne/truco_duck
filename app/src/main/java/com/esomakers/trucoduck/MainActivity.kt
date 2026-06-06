package com.esomakers.trucoduck

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var playerOneScore = 0
    private var playerTwoScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val playerOneName = intent.getStringExtra("player_one_name");
        val playerTwoName = intent.getStringExtra("player_two_name");

        findViewById<TextView>(R.id.player_one_name_label).text = playerOneName;
        findViewById<TextView>(R.id.player_two_name_label).text = playerTwoName;
    }
    fun onPointsButtonClicked(view: View) {
        val pointsToAdd = when (view.id) {
            R.id.player_one_btn_counter_one, R.id.player_two_btn_counter_one -> 1
            R.id.player_one_btn_counter_three, R.id.player_two_btn_counter_three -> 3
            R.id.player_one_btn_counter_six, R.id.player_two_btn_counter_six -> 6
            R.id.player_one_btn_counter_nine, R.id.player_two_btn_counter_nine -> 9
            R.id.player_one_btn_counter_twelve, R.id.player_two_btn_counter_twelve -> 12
            else -> 0
        }

        if (view.toString().contains("player_one")) {
            Log.e("CLICK p1", pointsToAdd.toString())
            playerOneScore += pointsToAdd
        } else {
            Log.e("CLICK p2", pointsToAdd.toString())
            playerTwoScore += pointsToAdd
        }

        findViewById<TextView>(R.id.player_one_points).text = "%02d".format(playerOneScore)
        findViewById<TextView>(R.id.player_two_points).text = "%02d".format(playerTwoScore)

        Log.e("CLICK P1 points", playerOneScore.toString())
        Log.e("CLICK p2 points", playerTwoScore.toString())
    }
}