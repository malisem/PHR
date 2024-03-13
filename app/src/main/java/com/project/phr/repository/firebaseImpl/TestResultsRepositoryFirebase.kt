package com.project.phr.repository.firebaseImpl

import com.google.firebase.firestore.FirebaseFirestore
import com.project.phr.model.TestResult
import com.project.phr.repository.TestResultsRepository
import com.project.phr.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.project.phr.util.safeCall
import kotlinx.coroutines.tasks.await


class TestResultsRepositoryFirebase : TestResultsRepository {
    private val dbRef = FirebaseFirestore.getInstance().collection("testResults")

    override suspend fun addTestResult(testResult: TestResult): Resource<Unit> = withContext(Dispatchers.IO) {
        safeCall {
            dbRef.document(testResult.id).set(testResult).await()
            Resource.Success(Unit)
        }
    }

    override suspend fun deleteTestResult(testResultId: String): Resource<Unit> = withContext(Dispatchers.IO) {
        safeCall {
            dbRef.document(testResultId).delete().await()
            Resource.Success(Unit)
        }
    }

    override suspend fun updateTestResult(testResult: TestResult): Resource<Unit> = withContext(Dispatchers.IO) {
        safeCall {
            dbRef.document(testResult.id).set(testResult).await()
            Resource.Success(Unit)
        }
    }

    override suspend fun getTestResult(testResultId: String): Resource<TestResult> = withContext(Dispatchers.IO) {
        safeCall {
            val documentSnapshot = dbRef.document(testResultId).get().await()
            val testResult = documentSnapshot.toObject(TestResult::class.java)
            if (testResult != null) Resource.Success(testResult) else Resource.Error("Test Result not found", null)
        }
    }

    override suspend fun getAllTestResults(): Resource<List<TestResult>> = withContext(Dispatchers.IO) {
        safeCall {
            val querySnapshot = dbRef.get().await()
            val testResults = querySnapshot.toObjects(TestResult::class.java)
            Resource.Success(testResults)
        }
    }
}
