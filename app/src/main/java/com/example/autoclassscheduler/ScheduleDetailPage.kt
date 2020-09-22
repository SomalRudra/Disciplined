package com.example.autoclassscheduler

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.autoclassscheduler.Database.ScheduleModel
import com.example.autoclassscheduler.Database.ScheduleViewModel
import com.example.autoclassscheduler.Utility.Constants
import com.example.autoclassscheduler.Utility.SharedPrefForLink
import com.example.autoclassscheduler.databinding.ListItemDetailBinding
import com.example.autoclassscheduler.Utility.timerUtility
import java.io.Serializable
import java.util.*

class ScheduleDetailPage : AppCompatActivity() {

    private lateinit var binding: ListItemDetailBinding
    private var timerUtility: timerUtility? = null
    private lateinit var prefProvider: SharedPrefForLink
    private lateinit var scheduleViewModel: ScheduleViewModel

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefProvider = SharedPrefForLink(applicationContext)
        timerUtility?.getCurrentTime()?.let { Log.i("timenow", it) }

        binding.submissionButton.setOnClickListener(View.OnClickListener {
            if(validinput()){
                intializeViewmodel()
                val subject = binding.subjectDetail.text.toString()
                val link = binding.classLinkDetail.text.toString()
                val hour = binding.timeDetailHour.text.toString().toInt()
                val minute = binding.timeDetailMinute.text.toString().toInt()
                var schedule = ScheduleModel(subject,link,hour,minute)
                Log.i("DEBUGapp",schedule.toString())
                scheduleViewModel.insert(schedule)
                finish()
            }else{
                Toast.makeText(applicationContext,"Details can not be empty!!",Toast.LENGTH_SHORT).show()
            }
//            try {
//                val replyIntent = Intent()
//                val subject = binding.subjectDetail.text.toString()
//                val link = binding.classLinkDetail.text.toString()
//                val hour = binding.timeDetailHour.text.toString()
//                val minute = binding.timeDetailMinute.text.toString()
//                setAlarm(hour.toInt(),minute.toInt(),link)
////                prefProvider.putString(Constants.subject_name,subject)
////                prefProvider.putString(Constants.link_key,link)
////                prefProvider.putInt(Constants.timeHour,hour.toInt())
////                prefProvider.putInt(Constants.timeMinute,minute.toInt())
//                var schedule = ScheduleModel(subject,link,hour.toInt(),minute.toInt())
//                replyIntent.putExtra(EXTRA_REPLY, schedule)
//                setResult(Activity.RESULT_OK, replyIntent)
//                finish()
//            }catch (e:Exception){
//                Log.i("novalue","No value entered")
//            }
        })

    }

    fun validinput(): Boolean {
        var empty =(binding.classLinkDetail.text.isEmpty() || binding.subjectDetail.text.isEmpty() ||
                binding.timeDetailHour.text.isEmpty() || binding.timeDetailMinute.text.isEmpty())
        var hourcheck = binding.timeDetailHour.text.toString().toInt() >=0 && binding.timeDetailHour.text.toString().toInt() <= 24
        var minutecheck = binding.timeDetailMinute.text.toString().toInt() >=0 && binding.timeDetailHour.text.toString().toInt() < 60
        if(!hourcheck){
            Toast.makeText(applicationContext,"Hour should be between 0 and 24",Toast.LENGTH_SHORT).show()
            return false
        }
        if(!minutecheck){
            Toast.makeText(applicationContext,"Minute should be between 0 and 59",Toast.LENGTH_SHORT).show()
            return false
        }
        return !empty
    }

    fun intializeViewmodel(){
        scheduleViewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun setAlarm(hour: Int, minute:Int,link:String) {
         var alarmMgr: AlarmManager? = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        prefProvider.putString(Constants.link_key,link)

        var alarmIntent : PendingIntent = Intent(applicationContext, AlertReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(applicationContext, 0, intent, 0)
        }

        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND,0)

            Toast.makeText(applicationContext,"Will notify you on "+hour+":"+minute,Toast.LENGTH_LONG).show()
        }

        alarmMgr?.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            alarmIntent
        )
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}