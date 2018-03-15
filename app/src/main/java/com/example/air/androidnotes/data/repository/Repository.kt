package com.example.air.androidnotes.data.repository

import android.arch.lifecycle.LiveData
import com.example.air.androidnotes.data.room.NotesEntity
import com.example.air.androidnotes.domain.Note

interface Repository {

    fun addNote(title: String, description: String)

    fun removeNote(note: NotesEntity)

    fun getNotesList(): LiveData<List<Note>>

}