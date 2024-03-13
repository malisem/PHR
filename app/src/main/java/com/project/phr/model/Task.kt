package com.project.phr.model

import java.util.Date

data class Task(
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var dueDate: Date = Date(),
    var completed: Boolean = false
) {
    // No-argument constructor required by Firebase Firestore
    constructor() : this("", "", "", Date(), false)
}
