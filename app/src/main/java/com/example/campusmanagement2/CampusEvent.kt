package com.example.campusmanagement2

import androidx.annotation.DrawableRes

data class CampusEvent(
    val title: String,
    val category: String,
    val date: String,
    val location: String,
    @DrawableRes val imageResId: Int,
    val tagColorHex: String = "#6366F1"
)