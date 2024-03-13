package com.project.phr.model

data class Note(
    var id: String = "",
    var title: String = "",
    var content: String = "",
    var date: Long = System.currentTimeMillis() // Storing the date as a timestamp
)
