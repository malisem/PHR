package com.project.phr.ui.medications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.phr.databinding.FragmentMedicationsBinding
import com.project.phr.repository.firebaseImpl.MedicationsRepositoryFirebase
import com.project.phr.util.Resource
import com.project.phr.util.autoCleared

class MedicationsFragment : Fragment() {
    private var binding: FragmentMedicationsBinding by autoCleared()
    private val viewModel: MedicationsViewModel by viewModels {
        MedicationsViewModel.MedicationsViewModelFactory(MedicationsRepositoryFirebase())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMedicationsBinding.inflate(inflater, container, false)
        setupRecyclerView()
        binding.addMedicationFab.setOnClickListener {
            // Implement your logic to add a new medication
            Toast.makeText(context, "Add Medication", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    private fun setupRecyclerView() {
        val adapter = MedicationsAdapter { medication ->
            // Example: Navigate to a detail view or perform other actions with the selected medication
            Toast.makeText(context, "Medication clicked: ${medication.name}", Toast.LENGTH_SHORT).show()
        }
        binding.medicationsRecyclerView.adapter = adapter // Updated to use medicationsRecyclerView
        binding.medicationsRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.medicationsStatus.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Show loading state, e.g., show a ProgressBar
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    // Update adapter with medications list and hide loading indicator
                    binding.progressBar.visibility = View.GONE
                    adapter.setTasks(resource.data ?: emptyList())
                }
                is Resource.Error -> {
                    // Hide loading indicator and show an error message
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "Error: ${resource.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
