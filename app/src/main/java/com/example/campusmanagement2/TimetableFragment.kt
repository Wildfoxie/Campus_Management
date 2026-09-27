package com.example.campusmanagement2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup

class TimetableFragment : Fragment() {

    private lateinit var rvTimetable: RecyclerView

    // Color Palette per Subject
    private val dsSubject = SubjectItem("Data Structures", "CS201", "09:00 AM - 10:00 AM", "Room 302", "Mon", "#4338CA", "#EEF2FF")
    private val dbSubject = SubjectItem("Database Systems", "CS204", "10:15 AM - 11:15 AM", "Lab 2", "Mon", "#059669", "#ECFDF5")
    private val osSubject = SubjectItem("Operating Systems", "CS203", "11:30 AM - 12:30 PM", "Room 105", "Tue", "#D97706", "#FFFBEB")
    private val netSubject = SubjectItem("Computer Networks", "CS205", "02:00 PM - 03:00 PM", "Room 401", "Wed", "#DC2626", "#FEF2F2")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timetable, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTimetable = view.findViewById(R.id.rvTimetable)
        rvTimetable.layoutManager = LinearLayoutManager(requireContext())

        val toggleGroup = view.findViewById<MaterialButtonToggleGroup>(R.id.toggleGroupView)

        // Initial Load -> Daily View
        showDailyView()

        toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnViewDaily -> showDailyView()
                    R.id.btnViewWeekly -> showWeeklyView()
                }
            }
        }
    }

    private fun showDailyView() {
        val todaySchedule = listOf(
            DaySchedule("Today (Monday)", listOf(dsSubject, dbSubject))
        )
        rvTimetable.adapter = WeeklyTimetableAdapter(todaySchedule)
    }

    private fun showWeeklyView() {
        val fullWeekSchedule = listOf(
            DaySchedule("Monday", listOf(dsSubject, dbSubject)),
            DaySchedule("Tuesday", listOf(osSubject)),
            DaySchedule("Wednesday", listOf(netSubject, dsSubject)),
            DaySchedule("Thursday", listOf(dbSubject, osSubject)),
            DaySchedule("Friday", listOf(netSubject))
        )
        rvTimetable.adapter = WeeklyTimetableAdapter(fullWeekSchedule)
    }
}