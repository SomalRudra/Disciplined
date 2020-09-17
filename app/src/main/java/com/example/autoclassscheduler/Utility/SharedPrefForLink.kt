package com.example.autoclassscheduler.Utility

import android.content.Context

class SharedPrefForLink(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("sharedpreforlink",0)

    fun putString(key:String,value:String){
        sharedPreferences.edit().putString(key,value).apply()
    }
    fun getString(key: String):String?{
        return sharedPreferences.getString(key,null)
    }

    fun putInt(key: String,value:Int){
        sharedPreferences.edit().putInt(key,value).apply()
    }

    fun getInt(key: String):Int?{
        return sharedPreferences.getInt(key,0)
    }

}