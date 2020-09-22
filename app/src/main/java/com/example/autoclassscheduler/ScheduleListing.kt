package com.example.autoclassscheduler

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autoclassscheduler.Database.ScheduleModel
import com.example.autoclassscheduler.Database.ScheduleViewModel
import com.example.autoclassscheduler.Utility.SharedPrefForLink
import com.example.autoclassscheduler.databinding.ScheduleListingBinding

class ScheduleListing :AppCompatActivity() {

    private lateinit var scheduleList : List<ScheduleModel>
    private lateinit var  binding:ScheduleListingBinding
    private lateinit var scheduleViewModel: ScheduleViewModel
    private lateinit var prefProvider: SharedPrefForLink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ScheduleListingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefProvider = SharedPrefForLink(applicationContext)


        //initialize list of schedule
        scheduleList = mutableListOf()


        //recyclerView done
        var adapter = RecyclerViewAdapter(scheduleList)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.adapter = adapter

        //viewmodel done
        scheduleViewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        scheduleViewModel.allSchedules.observe(this, androidx.lifecycle.Observer {
            schedules ->  schedules.let {
            scheduleList = it
            adapter.setSchedule(it)
        }
        })

        binding.fabToAdd.setOnClickListener( View.OnClickListener {
            var intent = Intent(this@ScheduleListing,ScheduleDetailPage::class.java)
            startActivity(intent)
        })
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(requestCode==newReqCode  && resultCode == Activity.RESULT_OK){
//                val schedule : ScheduleModel? = data?.getParcelableExtra("EXTRA_REPLY")
//                //Log.i("scheduleUSer",)
//            if (schedule != null) {
//                scheduleViewModel.insert(schedule)
//            }
////            var subject = prefProvider.getString(Constants.subject_name)
////            var link = prefProvider.getString(Constants.link_key)
////            var hour = prefProvider.getInt(Constants.timeHour)
////            var minute = prefProvider.getInt(Constants.timeMinute)
////            var schedule = hour?.let { subject?.let { it1 -> link?.let { it2 ->
////                minute?.let { it3 ->
////                    ScheduleModel(it1,
////                        it2, it, it3
////                    )
////                }
////            } } }
////            schedule?.let { scheduleViewModel.insert(it) }
//        }else{
//            Toast.makeText(applicationContext,
//            R.string.schedule_cant_be_saved,
//            Toast.LENGTH_LONG).show()
//        }
//    }
}