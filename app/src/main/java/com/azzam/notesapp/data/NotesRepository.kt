package com.azzam.notesapp.data

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.azzam.notesapp.data.local.Notes
import com.azzam.notesapp.data.local.room.NotesDao

class NotesRepository(private val notesDao: NotesDao) {

    val getAllNotes: LiveData<List<Notes>> = notesDao.getAllNotes()
    val sortByHighPriority: LiveData<List<Notes>> = notesDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<Notes>> = notesDao.sortByLowPriority()

    suspend fun insertNotes(notes: Notes) {
        notesDao.addNote(notes)
    }

    fun searchNoteByQuery(query: String) : LiveData<List<Notes>> {
        return notesDao.searchNotesByQuery(query)
    }

    suspend fun deleteAllData() {
        notesDao.deleteAllData()
    }

    suspend fun deleteNote(notes: Notes) {
        notesDao.deleteNote(notes)
    }

    suspend fun updateNotes(notes: Notes) {
        return notesDao.updateNote(notes)
    }
}