package com.nezrin.notesapplication

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel (application: Application) :AndroidViewModel(application) {


    val allNotes: LiveData<List<Note>>
    val repository: NoteRepository

    init {
        val dao = NoteDataBase.getDatabase(application).getNoteDao()

        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }
    fun deleteNote(note: Note)=viewModelScope.launch(Dispatchers.IO) {

        repository.delete(note)
    }
    fun updateNote(note: Note)=viewModelScope.launch(Dispatchers.IO) {

        repository.update(note)
    }
    fun addNote(note: Note)=viewModelScope.launch(Dispatchers.IO) {

        repository.insert(note)
    }
}