package com.esomakers.trucoduck

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.start)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        val btnStart = findViewById<Button>(R.id.start_button)

        btnStart.setOnClickListener {
            val intent = Intent(this, SetPlayersActivity::class.java)

            startActivity(intent)
        }


        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                Log.i("THEMA", "UI_MODE_NIGHT_NO")
            }


            Configuration.UI_MODE_NIGHT_YES -> {
                Log.i("THEMA", "UI_MODE_NIGHT_YES")
            }
        }

    }
}