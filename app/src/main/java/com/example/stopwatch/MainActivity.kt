package com.example.stopwatch

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

lateinit var clock: Chronometer
lateinit var startstop: Button
lateinit var reset: Button
 var checker = false
class MainActivity : AppCompatActivity() {
    companion object{
        //static constants
        val TAG = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")
        wireWidgets()
        startstop.setOnClickListener {
            if(!checker){
                clock.start()
                startstop.text = "STOP"
                startstop.setBackgroundColor(Color.RED)
            }
            else{
                clock.stop()
                startstop.text = "START"
                startstop.setBackgroundColor(Color.BLUE)
            }
            checker != checker
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


    private fun wireWidgets(){
        clock = findViewById(R.id.chronometer_main_stopwatch)
        startstop = findViewById(R.id.button_main_startStop)
        reset = findViewById(R.id.Button_main_Reset)
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