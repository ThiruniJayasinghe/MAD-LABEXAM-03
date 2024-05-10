package com.example.tapon

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tapon.R
import java.util.Random

class AdditionGameActivity : AppCompatActivity() {
    private lateinit var answerEditText: EditText
    private lateinit var submitAnswerButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var additionQuestionTextView: TextView

    private var number1 = Random().nextInt(100) + 1
    private var number2 = Random().nextInt(100) + 1
    private var score = 0
    private var attempts = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition)

        answerEditText = findViewById(R.id.answerEditText)
        submitAnswerButton = findViewById(R.id.submitAnswerButton)
        resultTextView = findViewById(R.id.resultTextView)
        additionQuestionTextView = findViewById(R.id.additionQuestionTextView)

        // Update the text of additionQuestionTextView with the numbers
        additionQuestionTextView.text = "What is the sum of $number1 and $number2?"

        submitAnswerButton.setOnClickListener {
            checkAnswer()
        }

        val restartButton: Button = findViewById(R.id.restartButton)
        restartButton.setOnClickListener {
            restartGame()
        }
    }

    private fun restartGame() {
        // Reset score, attempts, and generate new numbers
        score = 0
        attempts = 3
        number1 = Random().nextInt(100) + 1
        number2 = Random().nextInt(100) + 1

        // Update the question TextView
        additionQuestionTextView.text = "What is the sum of $number1 and $number2?"

        // Clear previous result
        resultTextView.text = ""

        // Clear answerEditText
        answerEditText.text.clear()
    }

    override fun onBackPressed() {
        // Navigate back to MainActivity
        super.onBackPressed()
        val intent = Intent(this@AdditionGameActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun checkAnswer() {
        val answerString = answerEditText.text.toString()
        if (answerString.isNotEmpty()) {
            val answer = answerString.toInt()
            val sum = number1 + number2
            if (answer == sum) {
                score += 20
                resultTextView.text = "Congratulations! You are the winner! Your score: $score"
            } else {
                attempts--
                if (attempts == 0) {
                    resultTextView.text = "Game over! The correct sum was $sum. Your final score: $score"
                } else {
                    resultTextView.text = "Incorrect! Try again. You have $attempts attempts left."
                }
            }
        } else {
            resultTextView.text = "Please enter a valid number."
        }

        number1 = Random().nextInt(100) + 1
        number2 = Random().nextInt(100) + 1
        answerEditText.text.clear()
    }
}

