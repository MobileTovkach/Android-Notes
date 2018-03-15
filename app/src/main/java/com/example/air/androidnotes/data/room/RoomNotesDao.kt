package com.example.air.androidnotes.data.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface RoomNotesDao {

    @Query(RoomContract.SELECT_NOTES_COUNT)
    fun getNotesTotal(): Flowable<Int>

    @Insert
    fun insertNote(notes: NotesEntity)

    @Delete
    fun deleteNote(note: NotesEntity)

    @Query(RoomContract.SELECT_NOTES)
    fun getAllNotes(): Flowable<List<NotesEntity>>

}