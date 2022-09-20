package com.example.stopwatch

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.Display
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

lateinit var clock: Chronometer
lateinit var startstop: Button
lateinit var reset: Button
lateinit var layout: ConstraintLayout
 var checker = false
var stopTime= 0L

class MainActivity : AppCompatActivity() {
    companion object{
        //static constants
        val TAG = ""
        val STATE_TIME = "display time"
        val STATE_RUNNING = "is running"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")
        wireWidgets()
        if(savedInstanceState != null){
            stopTime = savedInstanceState.getLong(STATE_TIME)
            clock.base = SystemClock.elapsedRealtime() - stopTime
            if(checker){
                clock.start()
            }
            else{
                clock.stop()
            }
        }

        startstop.setOnClickListener {
            if(!checker){
                if(stopTime == 0L) {
                    clock.base = SystemClock.elapsedRealtime()
                    clock.start()
                    startstop.text = "STOP"
                    startstop.setBackgroundColor(Color.RED)
                }
                else{
                    clock.base = SystemClock.elapsedRealtime() - (stopTime - clock.base)
                    clock.start()
                    startstop.text = "STOP"
                    startstop.setBackgroundColor(Color.RED)
                }
            }
            else{
                clock.stop()
                startstop.text = "START"
                startstop.setBackgroundColor(Color.BLUE)
                stopTime = SystemClock.elapsedRealtime()
            }
            checker = !checker
        }
        reset.setOnClickListener {
            if(checker){
                clock.stop()
                startstop.text = "START"
                startstop.setBackgroundColor(Color.BLUE)
            }
            clock.base = SystemClock.elapsedRealtime()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        //save the values in the key value pairs to the bundle
        if(checker){
        stopTime = SystemClock.elapsedRealtime() - clock.base
            outState.putBoolean(STATE_RUNNING, checker)
        }
        else{
            stopTime = clock.base
        }
        outState.putLong(STATE_TIME, stopTime)
        super.onSaveInstanceState(outState)
    }

    private fun wireWidgets(){
        clock = findViewById(R.id.chronometer_main_stopwatch)
        startstop = findViewById(R.id.button_main_startStop)
        reset = findViewById(R.id.Button_main_Reset)
        layout = findViewById(R.id.main_layout)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

}