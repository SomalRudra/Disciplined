package com.example.autoclassscheduler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autoclassscheduler.Database.ScheduleModel
import com.example.autoclassscheduler.databinding.DashboardBinding
import java.util.*
import kotlin.collections.ArrayList

class Dashboard : AppCompatActivity(){

    private lateinit var binding:DashboardBinding
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        linearLayoutManager = LinearLayoutManager(this)
//        binding.recyclerView.layoutManager = linearLayoutManager


        binding.manualFill.setOnClickListener {
            Log.i("navigation","navigation triggered")
            startActivity(Intent(this@Dashboard,ScheduleListing::class.java))
        }
        binding.imageToTextFill.setOnClickListener {
            Toast.makeText(applicationContext,"Under Development",Toast.LENGTH_SHORT).show()
        }

        val classesModel = ArrayList<ScheduleModel>()

//        classesModel.add(
//            ScheduleModel(
//                "DWDM",
//                "www.google.com",
//                Calendar.getInstance().time
//            )
//        )
//        classesModel.add(
//            ScheduleModel(
//                "DWDM",
//                "www.google.com",
//                Calendar.getInstance().time
//            )
//        )
//        classesModel.add(
//            ScheduleModel(
//                "DWDM",
//                "www.google.com",
//                Calendar.getInstance().time
//            )
//        )
//        classesModel.add(
//            ScheduleModel(
//                "DWDM",
//                "www.google.com",
//                Calendar.getInstance().time
//            )
//        )

//        val adapter = RecyclerViewAdapter(classesModel)
//        binding.recyclerView.adapter = adapter


    }



}
