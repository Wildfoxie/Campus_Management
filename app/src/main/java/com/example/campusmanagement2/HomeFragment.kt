package com.example.campusmanagement2

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var notificationHelper: NotificationHelper

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                triggerAlert()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationHelper = NotificationHelper(requireContext())

        val tvWelcomeUser = view.findViewById<TextView>(R.id.tvWelcomeUser)
        val btnQuickSchedule = view.findViewById<Button>(R.id.btnQuickSchedule)
        val btnQuickNotices = view.findViewById<Button>(R.id.btnQuickNotices)
        val btnQuickAlert = view.findViewById<Button>(R.id.btnQuickAlert)


        val sharedPref = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        val studentId = sharedPref.getString("studentId", "Student")
        tvWelcomeUser.text = "Welcome back, $studentId!"

        // Navigation actions
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)

        btnQuickSchedule.setOnClickListener {
            bottomNav?.selectedItemId = R.id.nav_timetable
        }

        btnQuickNotices.setOnClickListener {
            bottomNav?.selectedItemId = R.id.nav_notices
        }

        btnQuickAlert.setOnClickListener {
            checkAndSendNotification()
        }

//        val rvEvents = view.findViewById<RecyclerView>(R.id.rvCampusEvents)
//        rvEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val dummyEvents = listOf(
            CampusEvent(
                "Hackathon 2026: Build with AI",
                "COMPETITION",
                "Oct 12 • 09:00 AM",
                "Main Auditorium",
                R.drawable.ic_launcher_background,
                "#4338CA"
            ),
            CampusEvent(
                "Career Guidance & Resume Review",
                "SEMINAR",
                "Oct 15 • 02:00 PM",
                "Seminar Hall B",
                R.drawable.ic_launcher_background,
                "#059669"
            ),
            CampusEvent(
                "Annual Campus Cultural Fest",
                "FESTIVAL",
                "Oct 20 • 05:00 PM",
                "Open Air Theatre",
                R.drawable.ic_launcher_background,
                "#D97706"
            )
        )

        val rvEvents = view.findViewById<RecyclerView>(R.id.rvCampusEvents)
        rvEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvEvents.adapter = CampusEventsAdapter(dummyEvents)

        rvEvents.adapter = CampusEventsAdapter(dummyEvents)

        val tvSeeAllEvents = view.findViewById<TextView>(R.id.tvSeeAllEvents)
//        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)

        tvSeeAllEvents.setOnClickListener {
            bottomNav?.selectedItemId = R.id.nav_notices
        }
    }

    private fun checkAndSendNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    triggerAlert()
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        } else {
            triggerAlert()
        }
    }

    private fun triggerAlert() {
        notificationHelper.sendNotification(
            "URGENT: Campus Announcement",
            "Classes for the afternoon session are suspended due to severe weather."
        )
    }
}