package com.esomakers.trucoduck

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HistoryActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.history)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val historyTable = findViewById<LinearLayout>(R.id.table_container)
        val inflater = LayoutInflater.from(this)

        val repository = GameRepository(this)
        val gameList = repository.getSavedGames()

        historyTable.removeAllViews()

        if(gameList.isEmpty()) {
            return
        } else {
            gameList.forEachIndexed { index, game ->
                val viewLine = inflater.inflate(R.layout.history_line, historyTable, false)

                viewLine.findViewById<TextView>(R.id.game_number).text = "#${index + 1}"

                val winner = if (game.winner.playerPosition != 0) game.winner else game.loser
                val loser = if (game.winner.playerPosition == 0) game.winner else game.loser

                viewLine.findViewById<TextView>(R.id.winner_text_label).text = "${winner.name.trim()} (${winner.points} pts)"
                viewLine.findViewById<TextView>(R.id.loser_text_label).text = "${loser.name.trim()} (${loser.points} pts) "

                val formattedDate = game.gameDate.take(16).replace("T", "\n")
                viewLine.findViewById<TextView>(R.id.game_date).text = formattedDate

                historyTable.addView(viewLine)

            }
        }

    }
}