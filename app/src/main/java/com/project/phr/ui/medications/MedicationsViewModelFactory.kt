package com.project.phr.ui.medications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.phr.repository.MedicationsRepository // Adjust the import based on your actual package structure


class MedicationsViewModelFactory(private val medicationsRepository: MedicationsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicationsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MedicationsViewModel(medicationsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

