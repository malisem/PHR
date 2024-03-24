package com.project.phr.model

data class Note(
    var id: String = "",
    var title: String = "",
    var content: String = "",
    var date: String = "", // Use String to store date in a readable format
    var time: String = "" // Use String to store time
)

