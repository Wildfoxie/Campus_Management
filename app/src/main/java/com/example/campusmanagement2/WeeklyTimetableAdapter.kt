package com.example.campusmanagement2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeeklyTimetableAdapter(private val weeklySchedule: List<DaySchedule>) :
    RecyclerView.Adapter<WeeklyTimetableAdapter.DayViewHolder>() {

    class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDayName: TextView = itemView.findViewById(R.id.tvDayName)
        val containerSubjects: LinearLayout = itemView.findViewById(R.id.containerSubjects)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weekly_day, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val dayData = weeklySchedule[position]
        holder.tvDayName.text = dayData.dayName
        holder.containerSubjects.removeAllViews()

        val inflater = LayoutInflater.from(holder.itemView.context)
        for (subject in dayData.subjects) {
            val subView = inflater.inflate(R.layout.item_subject_pill, holder.containerSubjects, false)

            val cardContainer = subView.findViewById<LinearLayout>(R.id.pillContainer)
            val tvName = subView.findViewById<TextView>(R.id.tvSubjectName)
            val tvTime = subView.findViewById<TextView>(R.id.tvSubjectTime)

            tvName.text = "${subject.code} - ${subject.title}"
            tvTime.text = "${subject.timeSlot} • ${subject.room}"

            cardContainer.setBackgroundColor(Color.parseColor(subject.bgHex))
            tvName.setTextColor(Color.parseColor(subject.colorHex))

            holder.containerSubjects.addView(subView)
        }
    }

    override fun getItemCount(): Int = weeklySchedule.size
}