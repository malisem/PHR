package com.project.phr.ui.notes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.project.phr.databinding.FragmentAddEditNoteDialogBinding
import com.project.phr.model.Note
import com.project.phr.repository.firebaseImpl.NotesRepositoryFirebase
import java.util.Calendar
import java.util.UUID

class AddEditNoteDialogFragment : DialogFragment() {

    private var _binding: FragmentAddEditNoteDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NotesViewModel by viewModels {
        NotesViewModelFactory(NotesRepositoryFirebase()) // Assuming NotesRepositoryFirebase() is the correct instantiation
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddEditNoteDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()

        binding.buttonSelectDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                val dateString = "$dayOfMonth/${month + 1}/$year"
                binding.textViewDate.text = dateString
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            datePickerDialog.show()
        }

        binding.buttonSelectTime.setOnClickListener {
            val timePickerDialog = TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
                val timeString = String.format("%02d:%02d", hourOfDay, minute)
                binding.textViewTime.text = timeString
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)
            timePickerDialog.show()
        }

        // Handle btnSave click
        // Inside your btnSave click listener
        binding.btnSave.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            val content = binding.editTextContent.text.toString().trim()
            val date = binding.textViewDate.text.toString()
            val time = binding.textViewTime.text.toString()

            if (title.isEmpty() || content.isEmpty() || date.isEmpty() || time.isEmpty()) {
                Toast.makeText(context, "All fields must be filled", Toast.LENGTH_SHORT).show()
            } else {
                val note = Note(id = UUID.randomUUID().toString(), title = title, content = content, date = date, time = time)
                viewModel.saveNote(note)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



