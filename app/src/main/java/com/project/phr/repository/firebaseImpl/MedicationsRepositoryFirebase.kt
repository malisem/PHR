package com.project.phr.repository.firebaseImpl
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.project.phr.model.Medication
import com.project.phr.repository.MedicationsRepository
import com.project.phr.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.coroutines.channels.awaitClose

class MedicationsRepositoryFirebase : MedicationsRepository {

    private val medsRef = FirebaseFirestore.getInstance().collection("medications")

    // Ensure you have a safeCall function that handles exceptions and returns a Resource.
    // This should be somewhere in your utilities:
    private inline fun <T> safeCall(action: () -> Resource<T>): Resource<T> = try {
        action()
    } catch (e: Exception) {
        Resource.Error(e.localizedMessage ?: "An unknown error occurred", null)
    }

    override suspend fun addMedication(medication: Medication): Resource<Unit> = withContext(Dispatchers.IO) {
        safeCall {
            medsRef.document(medication.id).set(medication).await()
            Resource.Success(Unit) // Correctly return Unit for success without a specific value
        }
    }
    override suspend fun deleteMedication(medicationId: String): Resource<Unit> = withContext(Dispatchers.IO) {
        safeCall {
            medsRef.document(medicationId).delete().await() // Use .delete() for removing a document
            Resource.Success(Unit) // Correctly return Unit for success without a specific value
        }
    }

    override suspend fun updateMedication(medication: Medication): Resource<Unit> = withContext(Dispatchers.IO) {
        safeCall {
            medsRef.document(medication.id).set(medication).await()
            Resource.Success(Unit) // Use Unit for Kotlin's idiomatic approach
        }
    }


    override suspend fun getMedication(id: String): Resource<Medication> = withContext(Dispatchers.IO) {
        safeCall {
            val result = medsRef.document(id).get().await()
            val medication = result.toObject(Medication::class.java)
            if (medication != null) Resource.Success(medication) else Resource.Error("Medication not found", null)
        }
    }


    override suspend fun getAllMedications(): Resource<List<Medication>> = withContext(Dispatchers.IO) {
        safeCall {
            val querySnapshot = medsRef.get().await()
            val medications = querySnapshot.toObjects(Medication::class.java)
            Resource.Success(medications) // Return the list of medications
        }
    }

    override fun getMedicationsFlow(): Flow<Resource<List<Medication>>> = callbackFlow {
        val snapshotListener = medsRef.orderBy("name").addSnapshotListener { value, error ->
            val response = if (error != null) {
                Resource.Error<List<Medication>>(error.message ?: "An unknown error occurred")
            } else {
                Resource.Success(value?.toObjects(Medication::class.java) ?: listOf())
            }
            trySend(response).isSuccess
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getMedicationsLiveData(data: MutableLiveData<Resource<List<Medication>>>) {
        data.postValue(Resource.Loading())
        medsRef.orderBy("name").addSnapshotListener { snapshot, e ->
            if (e != null) {
                data.postValue(Resource.Error(e.localizedMessage as String))
            } else if (snapshot != null && !snapshot.isEmpty) {
                val medications = snapshot.toObjects(Medication::class.java)
                data.postValue(Resource.Success(medications))
            } else {
                data.postValue(Resource.Error("No Data"))
            }
        }
    }

    // Assume getMedicationsLiveData is similar in handling to getMedicationsFlow and adjusted accordingly.
}
