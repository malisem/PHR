package com.project.phr.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.phr.repository.NotesRepository

class NotesViewModelFactory(private val notesRepo: NotesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(notesRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
