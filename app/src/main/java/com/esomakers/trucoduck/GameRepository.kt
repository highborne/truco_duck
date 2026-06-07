package com.esomakers.trucoduck
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class GameRepository(private val context: Context) {
    private val fileName = "game_history.json"
    private val gson = Gson()

    fun gameSave(newGame: GameResult) {
        val savedGame = getSavedGames().toMutableList()
        savedGame.add(newGame)

        val jsonString = gson.toJson(savedGame)

        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            output -> output.write(jsonString.toByteArray())
        }
    }

    fun getSavedGames(): List<GameResult> {
        val file = File(context.filesDir, fileName)

        if (!file.exists()) return emptyList()

        return try {
            val jsonString = context.openFileInput(fileName).bufferedReader().use {it.readText()}
            val type = object : TypeToken<List<GameResult>>() {}.type
            gson.fromJson(jsonString, type) ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}


