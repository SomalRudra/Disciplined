package com.example.autoclassscheduler.Database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.*

@Parcelize
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
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}