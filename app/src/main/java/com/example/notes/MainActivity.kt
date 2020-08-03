package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.ui.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initToolbar()
    }

    private fun initToolbar(){
        mToolbar = mBinding.mainToolbar
        setSupportActionBar(mToolbar)
    }
}