package com.azzam.notesapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.azzam.notesapp.data.NotesRepository
import com.azzam.notesapp.data.local.Notes
import com.azzam.notesapp.data.local.room.NotesDao
import com.azzam.notesapp.data.local.room.NotesDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val notesDao: NotesDao = NotesDatabase.getDatabase(application).notesDao()
    private val notesRepository: NotesRepository = NotesRepository(notesDao)

    fun getAllNotes() : LiveData<List<Notes>> = notesRepository.getAllNotes

    fun insertNotes(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insertNotes(notes)
        }
    }

    fun searchNoteByQuery(query: String) : LiveData<List<Notes>> {
        return notesRepository.searchNoteByQuery(query)
    }
}