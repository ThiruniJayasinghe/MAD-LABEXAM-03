package com.example.tapon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val message = intent.getStringExtra("message")

        val gameOverTextView = findViewById<TextView>(R.id.gameOverTextView)
        gameOverTextView.text = message

        val restartButton = findViewById<Button>(R.id.restartButton)
        restartButton.setOnClickListener {
            // Start a new game by launching MainActivity
            val intent = Intent(this@GameOverActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
