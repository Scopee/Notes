package com.example.notes.ui

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.utils.AppTextWatcher
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_edit_note.*
import kotlinx.android.synthetic.main.note_item.*

class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).getToolbar().setNavigationOnClickListener {
            (activity as MainActivity).navController.popBackStack()
        }
        edit_name.addTextChangedListener (AppTextWatcher {
            val s = edit_name.text.toString()
            if (s.isEmpty())
                edit_name_layout.error = "Title cannot be empty"
            else
                edit_name_layout.error = null
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.edit_menu_ok, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit_menu_confirm -> {
                saveNote()
            }
        }
        return true
    }

    private fun saveNote() {

    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}