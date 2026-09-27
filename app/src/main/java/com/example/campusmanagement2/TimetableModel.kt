package com.example.campusmanagement2

data class SubjectItem(
    val title: String,
    val code: String,
    val timeSlot: String,
    val room: String,
    val dayOfWeek: String, // e.g., "Mon", "Tue", "Wed"
    val colorHex: String,
    val bgHex: String
)

data class DaySchedule(
    val dayName: String,
    val subjects: List<SubjectItem>
)