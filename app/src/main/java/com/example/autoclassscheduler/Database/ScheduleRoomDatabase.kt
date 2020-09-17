package com.example.autoclassscheduler.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(ScheduleModel::class), version = 1, exportSchema = false)
abstract class ScheduleRoomDatabase : RoomDatabase() {

    abstract fun scheduleDao():ScheduleDAO

    private class ScheduleDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var scheduleDao = database.scheduleDao()
                    scheduleDao.deleteAll()
                    var schedule = ScheduleModel("OS","laksdf.com",10,20)
                    scheduleDao.insert(schedule)
                }
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: ScheduleRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope):ScheduleRoomDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ScheduleRoomDatabase::class.java,
                    "schedule_database"
                ).addCallback(ScheduleDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}