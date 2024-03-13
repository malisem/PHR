package com.project.phr.ui.appointments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.phr.databinding.FragmentAppointmentsBinding
import java.util.Calendar


class AppointmentsFragment : Fragment() {
    private var _binding: FragmentAppointmentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAppointmentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTextAppointmentDate.setOnClickListener {
            showDatePickerDialog()
        }

        binding.editTextAppointmentTime.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            // Correcting the month value by adding 1 as Calendar.MONTH is zero-based
            val dateString = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth)
            binding.editTextAppointmentDate.setText(dateString)
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            val timeString = String.format("%02d:%02d", hourOfDay, minute)
            binding.editTextAppointmentTime.setText(timeString)
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
