package com.example.notes.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.database.models.Note

@Dao
interface NoteDao {
    @Query("SELECT * from notes")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE name LIKE :name")
    suspend fun findByName(name: String): Note

    @Query("SELECT * FROM notes where id Like :id")
    suspend fun findById(id: Long): Note

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)
}