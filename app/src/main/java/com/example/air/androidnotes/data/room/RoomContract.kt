package com.example.air.androidnotes.data.room

object RoomContract {

    const val DATABASE_NOTES = "notes.db"

    const val TABLE_NOTES = "notes"

    private const val SELECT_COUNT = "SELECT COUNT(*) FROM "
    private const val SELECT_FROM = "SELECT * FROM "

    const val SELECT_NOTES_COUNT = SELECT_COUNT + TABLE_NOTES
    const val SELECT_NOTES = SELECT_FROM + TABLE_NOTES

}