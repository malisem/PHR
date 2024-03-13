package com.project.phr.model

data class Appointment(
    val id: String = "",
    val doctorName: String = "",
    val location: String = "",
    val date: String = "", // Consider using a more appropriate date type
    val time: String = "", // Consider using a more appropriate time type
    val reminder: Boolean = false
)
