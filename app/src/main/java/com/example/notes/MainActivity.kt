package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.ui.MainFragment
import com.example.notes.viewmodel.NotesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: Toolbar

    lateinit var navController: NavController
    lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initToolbar()
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
    }

    fun getToolbar(): Toolbar = mToolbar

    private fun initToolbar() {
        mToolbar = mBinding.mainToolbar
        setSupportActionBar(mToolbar)
    }

}