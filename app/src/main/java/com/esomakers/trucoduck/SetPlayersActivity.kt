package com.esomakers.trucoduck

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class SetPlayersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_set_players)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.set_players)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etPlayerOneName = findViewById<TextInputEditText>(R.id.player_one_name)
        val etPlayerTwoName = findViewById<TextInputEditText>(R.id.player_two_name)
        val btnInitializerGame= findViewById<Button>(R.id.set_players_button)

        btnInitializerGame.setOnClickListener {
            val playerOneName = etPlayerOneName.text.toString()
            val playerTwoName = etPlayerTwoName.text.toString()

            if(playerOneName.isEmpty() || playerTwoName.isEmpty()) {
                etPlayerOneName.error = "digite o nome dos jogadores"
                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("player_one_name", playerOneName)
            intent.putExtra("player_two_name", playerTwoName)

            startActivity(intent)
        }
    }
}