package com.example.air.androidnotes.data.room

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
interface RoomNotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(notes: NotesEntity)

    @Delete
    fun deleteNote(note: NotesEntity)

    @Update
    fun updateNote(note: NotesEntity)

    @Query(RoomContract.SELECT_NOTES)
    fun getAllNotes(): Flowable<List<NotesEntity>>

}