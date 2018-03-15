package com.example.air.androidnotes.data.repository

import android.arch.lifecycle.LiveData
import com.example.air.androidnotes.data.room.RoomNotesDataSource
import com.example.air.androidnotes.domain.Note
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepository @Inject constructor(
        private val roomNotesDataSource: RoomNotesDataSource): Repository {

    override fun getTotalNotes(): Flowable<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNote() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeNote() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNotesList(): LiveData<List<Note>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}