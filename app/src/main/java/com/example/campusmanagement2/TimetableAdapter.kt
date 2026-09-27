package com.example.campusmanagement2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TimetableAdapter(private val scheduleList: List<ScheduleItem>) :
    RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder>() {

    class TimetableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSubject: TextView = itemView.findViewById(R.id.tvSubjectName)
        val tvTime: TextView = itemView.findViewById(R.id.tvTimeSlot)
        val tvRoom: TextView = itemView.findViewById(R.id.tvRoomNumber)
        val tvInstructor: TextView = itemView.findViewById(R.id.tvInstructor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimetableViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_schedule, parent, false)
        return TimetableViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimetableViewHolder, position: Int) {
        val item = scheduleList[position]
        holder.tvSubject.text = item.subjectName
        holder.tvTime.text = item.timeSlot
        holder.tvRoom.text = "Room: ${item.roomNumber}"
        holder.tvInstructor.text = "Instructor: ${item.instructor}"
    }

    override fun getItemCount(): Int = scheduleList.size
}