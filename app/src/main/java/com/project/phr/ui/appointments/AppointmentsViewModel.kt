package com.project.phr.ui.appointments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.phr.model.Appointment
import com.project.phr.repository.AppointmentsRepository
import com.project.phr.util.Resource
import kotlinx.coroutines.launch

class AppointmentsViewModel(private val repo: AppointmentsRepository) : ViewModel() {
    // Implement ViewModel logic to interact with AppointmentsRepository
    // For example, to add an appointment:
    fun addAppointment(appointment: Appointment) {
        viewModelScope.launch {
            val result = repo.addAppointment(appointment)
            // Handle the result, e.g., update UI
        }
    }
    // Add more functions as needed
}
