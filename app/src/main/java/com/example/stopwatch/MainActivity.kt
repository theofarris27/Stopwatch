package com.example.stopwatch

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import kotlinx.coroutines.NonCancellable.start

lateinit var clock: Chronometer
lateinit var startstop: Button
lateinit var reset: Button
 var checker = false
var stopTime= 0L
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
        clock.start()
      clock.stop()
        startstop.setOnClickListener {
            if(!checker){
                if(stopTime == 0L){
                    clock.base = SystemClock.elapsedRealtime()
                    stopTime = SystemClock.elapsedRealtime()
                }
                clock.start()
                startstop.text = "STOP"
                startstop.setBackgroundColor(Color.RED)

                clock.base = SystemClock.elapsedRealtime() - (stopTime - clock.base)
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