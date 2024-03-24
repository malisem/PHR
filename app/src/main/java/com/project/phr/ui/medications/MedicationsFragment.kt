package com.project.phr.ui.medications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.phr.R
import com.project.phr.SwipeToDeleteCallback
import com.project.phr.databinding.FragmentMedicationsBinding
import com.project.phr.repository.firebaseImpl.MedicationsRepositoryFirebase
import com.project.phr.util.Resource

class MedicationsFragment : Fragment() {

    private var _binding: FragmentMedicationsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MedicationsViewModel by viewModels {
        MedicationsViewModelFactory(MedicationsRepositoryFirebase())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMedicationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViewAndSwipeToDelete()
    }

    private fun setupRecyclerViewAndSwipeToDelete() {
        val adapter = MedicationsAdapter { medicationId ->
            // This might not be needed if you're focusing on swipe to delete.
        }
        binding.medicationsRecyclerView.adapter = adapter
        binding.medicationsRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.medicationsStatus.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    Log.d("MedicationsFragment", "Submitting list with size: ${resource.data?.size}")
                    adapter.submitList(resource.data)
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Error -> {
                    Toast.makeText(context, "Error: ${resource.message}", Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        val deleteIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_delete)!!
        val swipeToDeleteCallback = SwipeToDeleteCallback(adapter, deleteIcon) { position ->
            val medicationId = adapter.currentList[position].id
            viewModel.deleteMedication(medicationId)
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.medicationsRecyclerView)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

