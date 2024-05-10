package com.example.tapon

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var guessEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var additionGameButton: Button

    private var randomNumber = Random.nextInt(1, 101)
    private var attempts = 5
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        guessEditText = findViewById(R.id.guessEditText)
        submitButton = findViewById(R.id.submitButton)
        resultTextView = findViewById(R.id.resultTextView)

        additionGameButton = findViewById(R.id.additionGameButton)

        additionGameButton.setOnClickListener {
            // Start the addition game activity when the button is clicked
            val intent = Intent(this, AdditionGameActivity::class.java)
            startActivity(intent)
        }
        submitButton.setOnClickListener {
            checkGuess()
        }
    }

    private fun checkGuess() {
        val guess = guessEditText.text.toString().toIntOrNull()

        if (guess != null) {
            if (guess == randomNumber) {
                // Increase score for correct guess
                score += 10
                goToGameOverScreen("Congratulations! You guessed the correct number. Your score: $score")
            } else {
                attempts--
                if (attempts == 0) {
                    goToGameOverScreen("Game over! The correct number was $randomNumber. Your final score: $score")
                } else {
                    // Decrease score for each incorrect guess
                    score -= 2
                    val resultTextView = findViewById<TextView>(R.id.resultTextView)
                    resultTextView.text = "Wrong guess. You have $attempts attempts left. Your score: $score"
                }
            }
        } else {
            val resultTextView = findViewById<TextView>(R.id.resultTextView)
            resultTextView.text = "Please enter a valid number."
        }
        guessEditText.text.clear()
    }


    private fun goToGameOverScreen(message: String) {
        val intent = Intent(this, GameOverActivity::class.java)
        intent.putExtra("message", message)
        startActivity(intent)
        finish()
    }

}
