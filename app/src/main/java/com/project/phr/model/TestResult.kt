package com.project.phr.model

import java.util.Date

data class TestResult(
    var id: String = "",
    var testName: String = "",
    var resultUri: String = "", // URI or reference to the image of the result
    var date: Date = Date() // Using java.util.Date for the demonstration
)
