package com.example.notes.viewmodel

import androidx.lifecycle.LiveData
import com.example.notes.database.dao.NoteDao
import com.example.notes.database.models.Note

class NotesRepository (private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }
}