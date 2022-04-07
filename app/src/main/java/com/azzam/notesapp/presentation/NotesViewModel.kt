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

    val sortByHighPriority : LiveData<List<Notes>> = notesRepository.sortByHighPriority
    val sortByLowPriority : LiveData<List<Notes>> = notesRepository.sortByLowPriority

    fun getAllNotes() : LiveData<List<Notes>> = notesRepository.getAllNotes

    fun insertNotes(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insertNotes(notes)
        }
    }

    fun searchNoteByQuery(query: String) : LiveData<List<Notes>> {
        return notesRepository.searchNoteByQuery(query)
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteAllData()
        }
    }

    fun deleteNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteNote(notes)
        }
    }

    fun updateData(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.updateNotes(notes)
        }
    }

}