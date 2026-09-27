package com.example.campusmanagement2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoticeAdapter(private val noticeList: List<Notice>) :
    RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {

    class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvNoticeTitle)
        val tvDate: TextView = itemView.findViewById(R.id.tvNoticeDate)
        val tvDesc: TextView = itemView.findViewById(R.id.tvNoticeDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notice, parent, false)
        return NoticeViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val notice = noticeList[position]
        holder.tvTitle.text = notice.title
        holder.tvDate.text = notice.date
        holder.tvDesc.text = notice.description
    }

    override fun getItemCount(): Int = noticeList.size
}