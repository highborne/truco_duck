package com.esomakers.trucoduck

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
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

        val callback = object : androidx.activity.OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = android.content.Intent(applicationContext, StartActivity::class.java).apply {
                    flags = android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP or android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
                startActivity(intent)

                this@SetPlayersActivity.finish()
            }
        }

        onBackPressedDispatcher.addCallback(this, callback)

        btnInitializerGame.setOnClickListener {
            val playerOneName = etPlayerOneName.text.toString()
            val playerTwoName = etPlayerTwoName.text.toString()

            if(playerOneName.isEmpty()) {
                etPlayerOneName.error = getString(R.string.player_name_error)
                return@setOnClickListener
            }

            if(playerTwoName.isEmpty()) {
                etPlayerTwoName.error = getString(R.string.player_name_error)
                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("player_one_name", playerOneName)
            intent.putExtra("player_two_name", playerTwoName)

            startActivity(intent)
        }
    }
}