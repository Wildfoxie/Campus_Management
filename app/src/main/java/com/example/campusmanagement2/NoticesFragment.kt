package com.example.campusmanagement2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoticesFragment : Fragment(R.layout.fragment_notices) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvNotices = view.findViewById<RecyclerView>(R.id.rvNotices)
        rvNotices.layoutManager = LinearLayoutManager(requireContext())

        // Mock Notice Data
        val sampleNotices = listOf(
            Notice(
                "Mid-Semester Exam Schedule",
                "Oct 15, 2026",
                "The mid-semester timetable has been released. Check your department portal."
            ),
            Notice(
                "Annual Tech Symposium '26",
                "Nov 02, 2026",
                "Register for coding competitions, hackathons, and guest lectures on tech trends."
            ),
            Notice(
                "Library Maintenance Closure",
                "Nov 10, 2026",
                "Central library will remain closed for server upgrades from 9 AM to 2 PM."
            ),
            Notice(
                "Hackathon 2026: Build with AI",
                "Oct 12, 2026",
                "Main Auditorium"
            ),
            Notice(
                "Career Guidance & Resume Review",
                "Oct 15, 2026",
                "Seminar Hall B"
            ),
            Notice(
                "Annual Campus Cultural Fest",
                "Oct 20, 2026",
                "Open Air Theatre"
            )
        )

        rvNotices.adapter = NoticeAdapter(sampleNotices)
    }
}