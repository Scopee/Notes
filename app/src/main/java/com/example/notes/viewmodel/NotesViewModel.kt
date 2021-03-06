package com.example.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.database.NotesDatabase
import com.example.notes.database.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NotesRepository
    private val allNotes: LiveData<List<Note>>
    private lateinit var mLastDeleted: Note

    init {
        val noteDao = NotesDatabase.getDatabase(application).noteDao()
        repository = NotesRepository(noteDao)
        allNotes = repository.allNotes
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
        mLastDeleted = note
    }

    fun recover() = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(mLastDeleted)
        mLastDeleted
    }

    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun findNoteById(id: Long, callback: (Note) -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            val note = repository.findNoteById(id)
            callback(note)
        }
    }

}