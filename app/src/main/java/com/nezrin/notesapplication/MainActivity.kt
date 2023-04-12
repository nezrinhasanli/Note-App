package com.nezrin.notesapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nezrin.notesapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {
    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.notesRV.layoutManager=LinearLayoutManager(this)

        val noteRVAdapter=NoteRVAdapter(this,this)

        binding.notesRV.adapter=noteRVAdapter
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).
        get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this,Observer{ list ->

            list?.let {
                noteRVAdapter.updateList(it)
            }
        })

        binding.idFAB.setOnClickListener {
            val intent=Intent(this@MainActivity,AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(note: Note) {
        val intent=Intent(this@MainActivity,AddEditNoteActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteID",note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {

        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.noteTitle} Deleted",Toast.LENGTH_LONG).show()
    }
}