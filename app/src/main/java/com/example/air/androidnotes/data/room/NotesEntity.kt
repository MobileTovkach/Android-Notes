package com.example.air.androidnotes.data.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = RoomContract.TABLE_NOTES)
data class NotesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var titleNotes: String,
    var descriptionNotes: String
)
