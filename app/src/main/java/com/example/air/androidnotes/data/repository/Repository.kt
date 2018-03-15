package com.example.air.androidnotes.data.repository

import android.arch.lifecycle.LiveData
import com.example.air.androidnotes.domain.Note
import io.reactivex.Flowable

interface Repository {

    fun getTotalNotes(): Flowable<Int>

    fun addNote()

    fun removeNote()

    fun getNotesList(): LiveData<List<Note>>

}