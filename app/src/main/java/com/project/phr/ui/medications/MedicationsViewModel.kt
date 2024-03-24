package com.project.phr.ui.medications

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.phr.model.Medication
import com.project.phr.repository.MedicationsRepository
import com.project.phr.util.Resource
import kotlinx.coroutines.launch

class MedicationsViewModel(private val medicationsRepository: MedicationsRepository) : ViewModel() {

    private val _medicationsStatus = MutableLiveData<Resource<List<Medication>>>()
    val medicationsStatus: LiveData<Resource<List<Medication>>> = _medicationsStatus

    init {
        fetchMedications()
    }

    private fun fetchMedications() {
        viewModelScope.launch {
            _medicationsStatus.value = Resource.Loading()
            val result = medicationsRepository.getAllMedications()
            _medicationsStatus.value = result
        }
    }

    fun deleteMedication(medicationId: String) {
        Log.d("MedicationsViewModel", "Attempting to delete medication with ID: $medicationId")
        viewModelScope.launch {
            try {
                medicationsRepository.deleteMedication(medicationId)
                Log.d("MedicationsViewModel", "Medication deleted, refreshing list")
                fetchMedications() // Refresh list after deletion
            } catch (e: Exception) {
                Log.e("MedicationsViewModel", "Error deleting medication", e)
                // Handle the error state here, e.g., by updating a LiveData to display an error message
            }
        }
    }
}



