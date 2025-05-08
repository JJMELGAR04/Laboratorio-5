package com.falconteam.laboratorio3.models

import java.util.Date

data class Card(
    val pos: Int = 0,
    val title: String,
    val description: String,
    val endDate: Date = Date(),
    val checked: Boolean = false,
)



data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val endDate: Date = Date(),
    val isCompleted: Boolean = false
)