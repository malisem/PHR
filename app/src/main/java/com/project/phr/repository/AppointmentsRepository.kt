package com.project.phr.repository

import com.project.phr.model.Appointment
import com.project.phr.util.Resource

interface AppointmentsRepository {
    suspend fun addAppointment(appointment: Appointment): Resource<Void>
    suspend fun deleteAppointment(appointmentId: String): Resource<Void>
    suspend fun getAppointments(): Resource<List<Appointment>>
    // Add other necessary operations
}
