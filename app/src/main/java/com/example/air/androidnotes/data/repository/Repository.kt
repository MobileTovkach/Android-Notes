package com.example.air.androidnotes.data.repository

import android.arch.lifecycle.LiveData
import com.example.air.androidnotes.data.room.NotesEntity
import com.example.air.androidnotes.domain.Note
import io.reactivex.Flowable

interface Repository {

    fun getTotalNotes(): Flowable<Int>

    fun addNote(title: String, description: String)

    fun removeNote(note: NotesEntity)

    fun getNotesList(): LiveData<List<Note>>

}