package com.example.autoclassscheduler

import android.os.Bundle
import android.view.WindowManager
import android.view.WindowManager.LayoutParams
import android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
import androidx.appcompat.app.AppCompatActivity


class WakeupScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        // Important: have to do the following in order to show without unlocking
//        this.window.setFlags(
//            LayoutParams.FLAG_FULLSCREEN or
//                    LayoutParams.FLAG_SHOW_WHEN_LOCKED or
//                    LayoutParams.FLAG_TURN_SCREEN_ON,
//            LayoutParams.FLAG_FULLSCREEN or
//                    LayoutParams..FLAG_SHOW_WHEN_LOCKED or
//                    LayoutParams.FLAG_TURN_SCREEN_ON
//        )
    }
}
