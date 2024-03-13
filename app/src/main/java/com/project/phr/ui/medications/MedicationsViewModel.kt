package com.project.phr.ui.medications

import androidx.lifecycle.*
import com.project.phr.model.Medication
import com.project.phr.repository.MedicationsRepository
import com.project.phr.util.Resource
import kotlinx.coroutines.launch

class MedicationsViewModel(private val medsRepo: MedicationsRepository) : ViewModel() {
    private val _medicationsStatus: MutableLiveData<Resource<List<Medication>>> = MutableLiveData()
    val medicationsStatus: LiveData<Resource<List<Medication>>> = _medicationsStatus

    init {
        fetchMedications()
    }

    private fun fetchMedications() {
        viewModelScope.launch {
            _medicationsStatus.value = Resource.Loading()
            _medicationsStatus.value = medsRepo.getAllMedications()
        }
    }

    fun addMedication(medication: Medication) {
        viewModelScope.launch {
            medsRepo.addMedication(medication)
            fetchMedications()
        }
    }

    fun deleteMedication(medicationId: String) {
        viewModelScope.launch {
            medsRepo.deleteMedication(medicationId)
            fetchMedications()
        }
    }

    fun updateMedication(medication: Medication) {
        viewModelScope.launch {
            medsRepo.updateMedication(medication)
            fetchMedications()
        }
    }

    class MedicationsViewModelFactory(private val medsRepo: MedicationsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return MedicationsViewModel(medsRepo) as T
        }
    }
}
