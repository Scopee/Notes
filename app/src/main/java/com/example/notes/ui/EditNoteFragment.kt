package com.example.notes.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.database.models.Note
import com.example.notes.utils.AppTextWatcher
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_edit_note.*

class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {

    private lateinit var mNote: Note
    private var isEdit = false

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        if (arguments?.getLong("id") != null) {
            setNote(arguments?.getLong("id")!!)
        }
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).getToolbar().setNavigationOnClickListener {
            checkChanges()
        }
        edit_name.addTextChangedListener(AppTextWatcher {
            val s = edit_name.text.toString()
            if (s.isEmpty())
                edit_name_layout.error = "Title cannot be empty"
            else
                edit_name_layout.error = null
        })
    }

    private fun checkChanges() {
        val title = edit_name.text.toString()
        val text = edit_text.text.toString()

        if (isEdit) {
            if (title != mNote.name || text != mNote.text) {
                askExit()
            }
        } else {
            if (title.isNotEmpty() || text.isNotEmpty()) {
                askExit()
            }
        }
        (activity as MainActivity).navController.popBackStack()
    }

    private fun askExit() {
        Snackbar.make(requireView(), "Are you sure want exit without save?", Snackbar.LENGTH_LONG)
            .setAction("Yes") {
                (activity as MainActivity).navController.popBackStack()
            }
            .show()
    }

    private fun setNote(id: Long) {
        (activity as MainActivity).notesViewModel.findNoteById(id) {
            mNote = it
            edit_name.setText(mNote.name)
            edit_text.setText(mNote.text)
            isEdit = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.edit_menu_ok, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit_menu_confirm -> {
                saveNote()
                (activity as MainActivity).navController.popBackStack()
            }
        }
        return true
    }

    private fun saveNote() {
        val title = edit_name.text.toString()
        val text = edit_text.text.toString()

        if (isEdit) {
            mNote.name = title
            mNote.text = text
            (activity as MainActivity).notesViewModel.update(mNote)
        } else
            (activity as MainActivity).notesViewModel.insert(Note(title, text))
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}