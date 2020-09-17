package com.example.autoclassscheduler.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "schedule")
data class ScheduleModel(
    @ColumnInfo(name = "subjectName")
    var subjectName: String,

    @ColumnInfo(name = "link")
    var link:String,

    @ColumnInfo(name = "timeHour")
    var timeHour:Int,

    @ColumnInfo(name = "timeMinute")
    var timeMinute:Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}