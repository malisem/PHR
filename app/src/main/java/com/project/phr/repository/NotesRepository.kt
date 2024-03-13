package com.project.phr.repository

import com.project.phr.model.Note
import com.project.phr.util.Resource

interface NotesRepository {
    suspend fun addNote(note: Note): Resource<Unit>
    suspend fun deleteNote(noteId: String): Resource<Unit>
    suspend fun updateNote(note: Note): Resource<Unit>
    suspend fun getNote(id: String): Resource<Note>
    suspend fun getAllNotes(): Resource<List<Note>>
}

