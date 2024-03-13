package com.project.phr.repository.firebaseImpl

import com.google.firebase.firestore.FirebaseFirestore
import com.project.phr.model.Note
import com.project.phr.repository.NotesRepository
import com.project.phr.util.Resource
import com.project.phr.util.safeCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.tasks.await


class NotesRepositoryFirebase : NotesRepository {
    private val notesRef = FirebaseFirestore.getInstance().collection("notes")

    override suspend fun addNote(note: Note): Resource<Unit> = withContext(Dispatchers.IO) {
        safeCall {
            notesRef.document(note.id).set(note).await()
            Resource.Success(Unit)
        }
    }

    override suspend fun deleteNote(noteId: String): Resource<Unit> = withContext(Dispatchers.IO) {
        safeCall {
            notesRef.document(noteId).delete().await()
            Resource.Success(Unit)
        }
    }

    override suspend fun updateNote(note: Note): Resource<Unit> = withContext(Dispatchers.IO) {
        safeCall {
            notesRef.document(note.id).set(note).await()
            Resource.Success(Unit)
        }
    }

    override suspend fun getNote(id: String): Resource<Note> = withContext(Dispatchers.IO) {
        safeCall {
            val snapshot = notesRef.document(id).get().await() // Using await() here
            val note = snapshot.toObject(Note::class.java)
            note?.let {
                Resource.Success(it) // Ensure 'it' is used in a lambda where 'note' is the context
            } ?: Resource.Error("Note not found")
        }
    }


    override suspend fun getAllNotes(): Resource<List<Note>> = withContext(Dispatchers.IO) {
        safeCall {
            val snapshot = notesRef.get().await()
            Resource.Success(snapshot.toObjects(Note::class.java))
        }
    }
}

