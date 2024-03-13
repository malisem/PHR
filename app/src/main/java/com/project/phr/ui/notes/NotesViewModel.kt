package com.project.phr.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.phr.model.Note
import com.project.phr.repository.NotesRepository
import com.project.phr.util.Resource
import kotlinx.coroutines.launch

class NotesViewModel(private val notesRepo: NotesRepository) : ViewModel() {

    private val _notes: MutableLiveData<Resource<List<Note>>> = MutableLiveData()
    val notes: LiveData<Resource<List<Note>>> = _notes

    private val _operationStatus: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val operationStatus: LiveData<Resource<Unit>> = _operationStatus

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            _notes.postValue(Resource.Loading())
            val result = notesRepo.getAllNotes()
            _notes.postValue(result)
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            _operationStatus.postValue(Resource.Loading())
            val result = notesRepo.addNote(note)
            _operationStatus.postValue(result)
            getNotes()
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            _operationStatus.postValue(Resource.Loading())
            val result = notesRepo.deleteNote(noteId)
            _operationStatus.postValue(result)
            getNotes()
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            _operationStatus.postValue(Resource.Loading())
            val result = notesRepo.updateNote(note)
            _operationStatus.postValue(result)
            getNotes()
        }
    }
}
