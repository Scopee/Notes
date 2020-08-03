package com.example.notes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.database.models.Note
import com.example.notes.R
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NotesViewHolder>() {

    private var notesList = listOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int = notesList.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.noteName.text = notesList[position].name
        if (notesList[position].text.length > 16)
            holder.noteText.text = "${notesList[position].text.subSequence(0, 16)}..."
        else
            holder.noteText.text = notesList[position].text
    }

    fun setList(list: List<Note>) {
        notesList = list
    }

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noteName: TextView = view.note_name
        val noteText: TextView = view.note_text
    }

}