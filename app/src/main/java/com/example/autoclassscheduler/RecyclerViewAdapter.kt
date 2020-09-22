package com.example.autoclassscheduler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autoclassscheduler.Database.ScheduleModel
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.list_item_detail.view.*

class RecyclerViewAdapter (
    scheduleList: List<ScheduleModel>
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var schedules = mutableListOf<ScheduleModel>()

    init{
        schedules.addAll(scheduleList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item,parent,false)
        return  ViewHolder(view)
    }

    override fun getItemCount() = schedules.size

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(item = schedules[position])
    }

    internal fun setSchedule(schedules:List<ScheduleModel>){
        this.schedules.clear()
        this.schedules.addAll(schedules)
        Log.i("DEBUGappINADAPTER",schedules.toString())
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(item: ScheduleModel){
            Log.i("DEBUGappInvbind",item.subjectName+" "+item.link+" "+item.timeHour.toString()+" "+item.timeMinute.toString())
            itemView.subject_item.text = item.subjectName
            itemView.class_link_item.text = item.link
            itemView.time_item.text = item.timeHour.toString()+":"+item.timeMinute.toString()
//            val subjectName = itemView.findViewById<TextView>(R.id.subject_item)
//            val classLink = itemView.findViewById<TextView>(R.id.class_link_item)
//            val time = itemView.findViewById<TextView>(R.id.time_item)
//
//            subjectName.text = scheduleModel.subjectName
//            classLink.text = scheduleModel.link
//            time.text = scheduleModel.timeHour.toString()+":"+scheduleModel.timeMinute.toString()
        }
    }
}
