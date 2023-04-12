package com.nezrin.notesapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.nezrin.notesapplication.databinding.ActivityAddEditNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddEditNoteBinding
    private lateinit var viewModel: NoteViewModel
    var noteId=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)

        val noteType=intent.getStringExtra("noteType")
        if (noteType=="Edit"){
            val noteTitle=intent.getStringExtra("noteTitle")
            val noteDescription=intent.getStringExtra("noteDescription")
            noteId=intent.getIntExtra("noteId",-1)
            binding.idBtn.text = "Update Note"
            binding.idEdtNoteName.setText(noteTitle)
            binding.idEdtNoteDesc.setText(noteDescription)
        } else {
            binding.idBtn.text = "Save Note"
        }
        binding.idBtn.setOnClickListener {
            val noteTitle=binding.idEdtNoteName.text.toString()
            val noteDescription=binding.idEdtNoteDesc.text.toString()
            if (noteType=="Edit"){
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                    val sdf=SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String=sdf.format(Date())
                    val updateNote=Note(noteTitle,noteDescription,currentDate)
                    updateNote.id=noteId
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this,"Note Updated",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                    val sdf=SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String=sdf.format(Date())
                    viewModel.addNote(Note(noteTitle,noteDescription,currentDate))
                    Toast.makeText(this,"Note Added",Toast.LENGTH_SHORT).show()
                }
            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()
        }
    }
}