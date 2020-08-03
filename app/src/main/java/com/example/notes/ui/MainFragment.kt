package com.example.notes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Note
import com.example.notes.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {


    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: NoteAdapter

    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mRecyclerView = main_notes_view
        val notes = mutableListOf<Note>()
        notes.add(Note("Note 1", "Text 1"))
        notes.add(Note("Note 2", "Text 2"))
        notes.add(Note("Note 3", "Text 3"))
        mAdapter = NoteAdapter()
        mAdapter.setList(notes)
        mRecyclerView.adapter = mAdapter
    }
}