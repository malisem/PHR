package com.project.phr.model

data class Medication(
    var id: String = "",
    var name: String = "",
    val imageUrl: String = "",
    var dosage: String = "",
    var frequency: String = "",
    var completed: Boolean = false,
    var reminders: Boolean = false
)

