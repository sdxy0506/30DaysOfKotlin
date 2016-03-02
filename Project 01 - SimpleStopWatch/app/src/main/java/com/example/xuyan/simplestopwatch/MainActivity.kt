package com.example.xuyan.simplestopwatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    var mWatchTime = 0.0

    var mTimer: Timer? = null

    var isPause = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reset.setOnClickListener { View -> resetWatch() }
        play.setOnClickListener { View -> play() }
        pause.setOnClickListener { View -> pauseWatch() }
    }

    fun setWatchTime(watcher: TextView) {
        watcher.text = String.format("%.1f", mWatchTime)
        Log.e("test", mWatchTime.toString())
    }

    fun play() {
        if (!isPause) return
        mTimer = Timer()
        isPause = false
        mTimer?.schedule(timerTask {
            runOnUiThread({
                mWatchTime += 0.1
                setWatchTime(watch)
            })
        }, 10, 100)
    }

    fun pauseWatch() {
        mTimer?.cancel()
        mTimer = null
        isPause = true
    }

    fun resetWatch() {
        pauseWatch()
        mWatchTime = 0.0
        runOnUiThread({
            setWatchTime(watch)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        pauseWatch()
    }


}
