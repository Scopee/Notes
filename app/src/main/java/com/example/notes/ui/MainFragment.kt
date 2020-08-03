package com.example.notes.ui

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.MainActivity
import com.example.notes.Note
import com.example.notes.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {


    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: NoteAdapter

    override fun onStart() {
        super.onStart()
        initRecyclerView()
        activity?.title = "My notes"

        main_btn_create.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_mainFragment2_to_noteFragment)
        }

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