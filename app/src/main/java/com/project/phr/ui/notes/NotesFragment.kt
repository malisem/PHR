package com.project.phr.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.phr.databinding.FragmentNotesBinding
import com.project.phr.repository.firebaseImpl.NotesRepositoryFirebase
import com.project.phr.util.Resource

class NotesFragment : Fragment() {
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NotesViewModel by viewModels {
        NotesViewModelFactory(NotesRepositoryFirebase())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NotesAdapter(onEdit = { note ->
            // Implement note editing logic
        }, onDelete = { note ->
            viewModel.deleteNote(note.id)
        })

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.notesRecyclerView.adapter = adapter

        viewModel.notes.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Optionally handle loading state
                }
                is Resource.Success -> {
                    resource.data?.let { notes ->
                        adapter.submitList(notes)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(context, resource.message ?: "An error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.addNoteFab.setOnClickListener {
            AddEditNoteDialogFragment().show(childFragmentManager, "AddNoteDialog")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
