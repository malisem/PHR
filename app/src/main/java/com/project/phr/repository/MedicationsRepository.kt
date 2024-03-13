package com.project.phr.repository

import androidx.lifecycle.MutableLiveData
import com.project.phr.model.Medication
import com.project.phr.util.Resource
import kotlinx.coroutines.flow.Flow

interface MedicationsRepository {
    suspend fun addMedication(medication: Medication): Resource<Unit>
    suspend fun deleteMedication(medicationId: String): Resource<Unit>
    suspend fun updateMedication(medication: Medication): Resource<Unit>
    suspend fun getMedication(id: String): Resource<Medication>
    suspend fun getAllMedications(): Resource<List<Medication>>

    fun getMedicationsFlow(): Flow<Resource<List<Medication>>>
    fun getMedicationsLiveData(data: MutableLiveData<Resource<List<Medication>>>)
}
