package com.example.autoclassscheduler.Utility

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.PowerManager
import android.preference.PreferenceManager
import java.util.*

class timerUtility(context: Context) {

    //sharedpreference
    val sharedPrefrence:SharedPreferences = context.getSharedPreferences("sharedpref",0)


    private lateinit var wakeLock: PowerManager.WakeLock
    fun getCurrentTime():String{
        var timeNdate = Calendar.getInstance().time
        //var timeNow = timeNdate.hours.toString() + ":" + timeNdate.minutes.toString() + ":" + timeNdate.seconds.toString()
        return timeNdate.toString()
    }

    @SuppressLint("InvalidWakeLockTag")
    fun wakelocker(applicationContext: Context){
        if(wakeLock!=null)
            wakeLock?.release()
        var permissionFlag1 = PowerManager.FULL_WAKE_LOCK
        var permissionFlag2 = PowerManager.ACQUIRE_CAUSES_WAKEUP
        var permissionFlag3 = PowerManager.ON_AFTER_RELEASE

        var permissionFlag = permissionFlag1 ; permissionFlag2 ; permissionFlag3

        var pm : PowerManager = applicationContext.getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = pm.newWakeLock(permissionFlag,"tag")
        wakeLock?.acquire()
    }

    fun release(){
        wakeLock?.release()
    }

}