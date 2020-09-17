package com.example.autoclassscheduler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autoclassscheduler.Database.ScheduleModel

class RecyclerViewAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val inflater:LayoutInflater = LayoutInflater.from(context)
    private var schedules = emptyList<ScheduleModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        return  ViewHolder(inflater.inflate(R.layout.list_item,parent,false))
    }

    override fun getItemCount() = schedules.size

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(schedules[position])
    }

    internal fun setSchedule(schedules:List<ScheduleModel>){
        this.schedules = schedules
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(scheduleModel: ScheduleModel){
            val subjectName = itemView.findViewById<TextView>(R.id.subject_item)
            val classLink = itemView.findViewById<TextView>(R.id.class_link_item)
            val time = itemView.findViewById<TextView>(R.id.time_item)

            subjectName.text = scheduleModel.subjectName
            classLink.text = scheduleModel.link
            time.text = scheduleModel.timeHour.toString()+":"+scheduleModel.timeMinute.toString()
        }
    }
}
