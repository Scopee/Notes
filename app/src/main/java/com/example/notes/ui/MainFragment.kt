package com.example.notes.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.ui.objects.SwipeToDeleteCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(R.layout.fragment_main) {


    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: NoteAdapter

    override fun onStart() {
        super.onStart()
        initRecyclerView()
        activity?.title = "My notes"
        hideKeyboard()
        (activity as MainActivity).notesViewModel.getAllNotes().observe(this, Observer {
            Log.d("MainFragment", "observe")
            mAdapter.setList(it)
        })

        main_btn_create.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_mainFragment_to_noteFragment)
        }
    }

    private fun initRecyclerView() {
        mRecyclerView = main_notes_view
        mAdapter = NoteAdapter {
            val bundle = Bundle()
            bundle.putLong("id", it)
            (activity as MainActivity).navController.navigate(
                R.id.action_mainFragment_to_noteFragment,
                bundle
            )
        }
        mRecyclerView.adapter = mAdapter
        val itemTouchHelper =
            ItemTouchHelper(SwipeToDeleteCallback(mAdapter, activity as MainActivity) {
                (activity as MainActivity).notesViewModel.delete(it)
                showUndoSnackbar()
            })
        itemTouchHelper.attachToRecyclerView(mRecyclerView)
    }

    private fun showUndoSnackbar() {
        Snackbar.make(requireView(), "Recover Note", Snackbar.LENGTH_LONG)
            .setAction("Undo?") {
                (activity as MainActivity).notesViewModel.recover()
            }
            .show()
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            (activity as MainActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow((activity as MainActivity).window.decorView.windowToken, 0)
    }
}