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
    }

    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

}