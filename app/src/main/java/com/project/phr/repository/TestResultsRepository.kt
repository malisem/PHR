package com.project.phr.repository

import com.project.phr.model.TestResult
import com.project.phr.util.Resource

interface TestResultsRepository {
    suspend fun addTestResult(testResult: TestResult): Resource<Unit>
    suspend fun deleteTestResult(testResultId: String): Resource<Unit>
    suspend fun updateTestResult(testResult: TestResult): Resource<Unit>
    suspend fun getTestResult(testResultId: String): Resource<TestResult>
    suspend fun getAllTestResults(): Resource<List<TestResult>>
}
