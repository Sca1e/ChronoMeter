package com.example.chronometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var buttonStartPause : Button
    private lateinit var buttonReset: Button
    private lateinit var chronometer: Chronometer
    private lateinit var lapMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStartPause = findViewById(R.id.startPause)
        buttonReset = findViewById(R.id.reset)
        chronometer = findViewById(R.id.chronometer)
        lapMessage = findViewById(R.id.lapMessage)

        var ifPause : Boolean = false
        var timeWhenPressPause : Long = 0
        var i : Long = 0
        var doubleCheckPause : Boolean = false

        buttonStartPause.setOnClickListener{
            when (ifPause){
                true -> {
                    doubleCheckPause = true
                    buttonStartPause.text = "START"
                    timeWhenPressPause = SystemClock.elapsedRealtime() - chronometer.base
                    chronometer.stop()
                    ifPause = false
                    i++
                    lapMessage.text =  lapMessage.text.toString() + i + " - " + chronometer.text.toString() + " \n"
                    Toast.makeText(applicationContext, "Chronometr paused", Toast.LENGTH_SHORT).show()
                }
                false -> {
                    buttonStartPause.text = "PAUSE"
                    ifPause = true
                    chronometer.base = SystemClock.elapsedRealtime() - timeWhenPressPause
                    chronometer.start()
                    Toast.makeText(applicationContext, "Chronometr started", Toast.LENGTH_SHORT).show()
                }
            }
        }
        buttonReset.setOnClickListener {
            chronometer.stop()
            chronometer.base = SystemClock.elapsedRealtime()
            timeWhenPressPause = 0
            buttonStartPause.text = "START"
            ifPause = false
            lapMessage.text = ""
            i = 0
            Toast.makeText(applicationContext, "Chronometr reseted", Toast.LENGTH_SHORT).show()
        }
    }
}
