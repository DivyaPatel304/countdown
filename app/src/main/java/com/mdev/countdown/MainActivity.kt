package com.mdev.countdown

import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var chronometer: Chronometer
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var pauseButton: Button
    private lateinit var secondsInput: EditText
    private lateinit var startCountdownButton: Button
    private lateinit var countdownText: TextView

    private var isChronometerRunning = false
    private var chronometerBase: Long = 0
    private var countdownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chronometer = findViewById(R.id.chronometer)

        secondsInput = findViewById(R.id.seconds_input)
        startCountdownButton = findViewById(R.id.start_countdown_button)
        countdownText = findViewById(R.id.countdown_text)



        startCountdownButton.setOnClickListener {
            val seconds = secondsInput.text.toString().toLongOrNull()
            if (seconds != null) {
                startCountdown(seconds)
            }
        }
    }

    private fun startChronometer() {
        if (!isChronometerRunning) {
            chronometer.base = System.currentTimeMillis() - chronometerBase
            chronometer.start()
            isChronometerRunning = true
        }
    }

    private fun stopChronometer() {
        chronometer.stop()
        chronometerBase = System.currentTimeMillis() - chronometer.base
        isChronometerRunning = false
    }

    private fun pauseChronometer() {
        chronometer.stop()
        chronometerBase = System.currentTimeMillis() - chronometer.base
        isChronometerRunning = false
    }

    private fun startCountdown(seconds: Long) {
        countdownTimer?.cancel()

        countdownTimer = object : CountDownTimer(seconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                chronometer.setBase(SystemClock.elapsedRealtime()  - millisUntilFinished)
            }

            override fun onFinish() {
                countdownText.text = "Done!"
            }
        }

        countdownTimer?.start()
    }

}