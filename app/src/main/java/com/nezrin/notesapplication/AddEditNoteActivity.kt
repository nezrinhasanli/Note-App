package com.nezrin.notesapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nezrin.notesapplication.databinding.ActivityAddEditNoteBinding

class AddEditNoteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddEditNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}