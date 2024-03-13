package com.project.phr.model

data class Medication(
    var id: String = "",
    var name: String = "",
    var dosage: String = "",
    var frequency: String = "",
    var reminders: Boolean = false
)

