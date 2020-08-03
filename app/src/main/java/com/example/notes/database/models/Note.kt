package com.example.notes.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "text") var text: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)