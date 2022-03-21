package com.azzam.notesapp.data

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.azzam.notesapp.data.local.Notes
import com.azzam.notesapp.data.local.room.NotesDao

class NotesRepository(private val notesDao: NotesDao) {

    val getAllNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insertNotes(notes: Notes) {
        notesDao.addNote(notes)
    }

    fun searchNoteByQuery(query: String) : LiveData<List<Notes>> {
        return notesDao.searchNoteByQuery(query)
    }

}