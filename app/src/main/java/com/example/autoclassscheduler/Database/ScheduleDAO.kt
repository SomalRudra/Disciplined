package com.example.autoclassscheduler.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ScheduleDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(schedule:ScheduleModel)

    @Query("Select * from schedule ORDER BY timeHour ASC")
    fun listbytimeASC():LiveData<List<ScheduleModel>>

//    @Delete
//    suspend fun deleteSpecific(id:Int)

    @Query("Delete from schedule")
    suspend fun deleteAll()
}