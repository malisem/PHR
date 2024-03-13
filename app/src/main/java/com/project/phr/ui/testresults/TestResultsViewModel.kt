package com.project.phr.ui.testresults

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.phr.model.TestResult
import com.project.phr.repository.TestResultsRepository
import com.project.phr.util.Resource
import kotlinx.coroutines.launch

class TestResultsViewModel(private val repo: TestResultsRepository) : ViewModel() {
    // LiveData for test results, loading state, etc.
    // Implement functions to interact with the repo, e.g., add, delete, fetch test results.

    fun addTestResult(testResult: TestResult) {
        viewModelScope.launch {
            val result = repo.addTestResult(testResult)
            // Update UI based on result
        }
    }

    // Add more functions as necessary
}
