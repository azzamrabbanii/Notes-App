package com.azzam.notesapp.local

data class Notes (
    val id: Int,
    val tittle: String,
    val desc: String,
    val date: String,
    val priority: Priority
)