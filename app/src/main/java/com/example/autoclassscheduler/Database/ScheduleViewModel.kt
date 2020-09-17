package com.example.autoclassscheduler.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScheduleViewModel(application: Application): AndroidViewModel(application){

    private val repository:ScheduleRepository

    val allSchedules: LiveData<List<ScheduleModel>>

    init {
        val scheduleDAO:ScheduleDAO = ScheduleRoomDatabase.getDatabase(application,viewModelScope).scheduleDao()
        repository = ScheduleRepository(scheduleDAO)
        allSchedules = repository.allSchedules
    }

    fun insert(scheduleModel: ScheduleModel) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(scheduleModel)
    }
}