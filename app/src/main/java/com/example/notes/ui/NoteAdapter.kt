package com.example.notes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.database.models.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(val callback: (Long) -> Unit) :
    RecyclerView.Adapter<NoteAdapter.NotesViewHolder>() {

    private var mNotesList = listOf<Note>()
    private val maxTextLength = 64

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int = mNotesList.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.noteName.text = mNotesList[position].name
        if (mNotesList[position].text.length > maxTextLength)
            holder.noteText.text = "${mNotesList[position].text.subSequence(0, maxTextLength)}..."
        else
            holder.noteText.text = mNotesList[position].text
        holder.itemView.setOnClickListener {
            callback(mNotesList[position].id)
        }
    }

    fun getNoteByPosition(position: Int): Note = mNotesList[position]

    fun setList(list: List<Note>) {
        mNotesList = list
        notifyDataSetChanged()
    }

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noteName: TextView = view.note_name
        val noteText: TextView = view.note_text
    }

}