package com.example.air.androidnotes.data.room

import android.arch.persistence.room.*
import android.content.Context

@Database(entities = [(NotesEntity::class)],
        version = 1)
abstract class RoomNotesDataSource: RoomDatabase() {

    abstract fun dao(): RoomNotesDao

}

fun buildPersistentNotes(context: Context): RoomNotesDataSource = Room.databaseBuilder(
        context.applicationContext,
        RoomNotesDataSource::class.java,
        RoomContract.DATABASE_NOTES
).build()