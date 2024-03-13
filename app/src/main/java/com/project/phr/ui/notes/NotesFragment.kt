package com.project.phr.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.project.phr.databinding.FragmentNotesBinding
import com.project.phr.repository.firebaseImpl.NotesRepositoryFirebase

class NotesFragment : Fragment() {
    private var binding: FragmentNotesBinding? = null

    // Using 'viewModels' delegate for simplicity and automatic lifecycle handling.
    private val viewModel: NotesViewModel by viewModels {
        NotesViewModelFactory(NotesRepositoryFirebase())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup RecyclerView and other UI components here
        // Example: setupObservers()
    }

    // Setup LiveData observers for the ViewModel
    private fun setupObservers() {
        // Example LiveData observation:
        // viewModel.notes.observe(viewLifecycleOwner) { notes ->
        //     // Update your UI here
        // }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null // Avoiding memory leaks by nulling the binding reference
    }
}
