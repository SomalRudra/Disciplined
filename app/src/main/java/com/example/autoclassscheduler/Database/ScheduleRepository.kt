package com.example.autoclassscheduler.Database

import androidx.lifecycle.LiveData

class ScheduleRepository(private val scheduleDao: ScheduleDAO) {

    var allSchedules: LiveData<List<ScheduleModel>> = scheduleDao.listbytimeASC()

    suspend fun insert(scheduleModel: ScheduleModel) {
        scheduleDao.insert(scheduleModel)
    }

//    suspend fun deleteSpecific(id:Int){
//        scheduleDao.deleteSpecific(id)
//    }

    suspend fun deleteAll(){
        scheduleDao.deleteAll()
    }
}