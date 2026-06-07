package com.esomakers.trucoduck

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.Serializable
import java.time.LocalDateTime
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var playerOne: PlayerResult
    private lateinit var playerTwo: PlayerResult
    private lateinit var  gameResult: GameResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val playerOneName = intent.getStringExtra("player_one_name").toString()
        val playerTwoName = intent.getStringExtra("player_two_name").toString()

        playerOne = PlayerResult(name = playerOneName, points = 0, winNumber = 0, playerPosition = 0)
        playerTwo = PlayerResult(name = playerTwoName, points = 0, winNumber = 0,  playerPosition = 1)

        findViewById<TextView>(R.id.player_one_name_label).text = playerOneName
        findViewById<TextView>(R.id.player_two_name_label).text = playerTwoName

        val btnRoundsHistory = findViewById<Button>(R.id.history_rounds_button)
        val btnInfNames = findViewById<Button>(R.id.inf_names_buttons)
        val btnRoundsReset = findViewById<Button>(R.id.reset_rounds_button)

        btnRoundsHistory.setOnClickListener {
            showRoundsModal()
        }
        btnInfNames.setOnClickListener {
            val intent = Intent(this, SetPlayersActivity::class.java)
            startActivity(intent)
        }
        btnRoundsReset.setOnClickListener {
            playerOne.points = 0
            playerOne.winNumber = 0
            playerTwo.points = 0
            playerTwo.winNumber = 0

            findViewById<TextView>(R.id.player_one_points).text = "%02d".format(0)
            findViewById<TextView>(R.id.player_two_points).text = "%02d".format(0)
        }
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
            playerOne.points += pointsToAdd
            findViewById<TextView>(R.id.player_one_points).text = "%02d".format(playerOne.points)
        } else {
            playerTwo.points += pointsToAdd
            findViewById<TextView>(R.id.player_two_points).text = "%02d".format(playerTwo.points)
        }

        verifyEndOfGame()
    }
    fun verifyEndOfGame() {
        val currentDateTime = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss", Locale.getDefault())

        if(playerOne.points >= 12) {
            playerOne.winNumber += 1
            gameResult = GameResult(playerOne, playerTwo, formatter.format(currentDateTime))

            showWinnerScoreModal()
            return
        }

        if(playerTwo.points >= 12) {
            playerTwo.winNumber += 1
            gameResult = GameResult(playerTwo, playerOne, formatter.format(currentDateTime))

            showWinnerScoreModal()
            return
        }
    }
    fun showWinnerScoreModal() {
        val modalBuilder = AlertDialog.Builder(this)
        val inflater = layoutInflater

        val gameRepository = GameRepository(this)

        gameRepository.gameSave(gameResult)

        val resultModal = inflater.inflate(R.layout.modal_game_result, null)
        modalBuilder.setView(resultModal)

        val dialog = modalBuilder.create()

        resultModal.findViewById<TextView>(R.id.winner_name_label).text = gameResult.winner.name
        resultModal.findViewById<TextView>(R.id.winner_points).text = gameResult.winner.points.toString()

        resultModal.findViewById<TextView>(R.id.loser_name_label).text = gameResult.loser.name
        resultModal.findViewById<TextView>(R.id.loser_points).text = gameResult.loser.points.toString()

        resultModal.findViewById<Button>(R.id.btn_close_game_result_modal).setOnClickListener {
            playerOne.points = 0
            playerTwo.points = 0

            findViewById<TextView>(R.id.player_one_points).text = "%02d".format(0)
            findViewById<TextView>(R.id.player_two_points).text = "%02d".format(0)

            dialog.dismiss()
        }

        dialog.show()
    }
    fun showRoundsModal() {
        val modalBuilder = AlertDialog.Builder(this)
        val inflater = layoutInflater

        val viewModal = inflater.inflate(R.layout.modal_rounds, null)
        modalBuilder.setView(viewModal)

        val dialog = modalBuilder.create()

        viewModal.findViewById<TextView>(R.id.round_modal_player_one_name).text = playerOne.name
        viewModal.findViewById<TextView>(R.id.round_modal_player_one_wins).text = playerOne.winNumber.toString()

        viewModal.findViewById<TextView>(R.id.round_modal_player_two_name).text = playerTwo.name
        viewModal.findViewById<TextView>(R.id.round_modal_player_two_wins).text = playerTwo.winNumber.toString()

        viewModal.findViewById<Button>(R.id.btn_close_modal_rounds).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}

data class PlayerResult(val name: String, var points: Int, var winNumber: Int, val playerPosition: Int): Serializable
data class GameResult(var winner: PlayerResult, var loser: PlayerResult, var gameDate: String): Serializable