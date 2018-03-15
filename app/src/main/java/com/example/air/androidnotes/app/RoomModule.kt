package com.example.air.androidnotes.app

import android.content.Context
import com.example.air.androidnotes.data.room.RoomNotesDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomNotesDataSource(context: Context) =
            RoomNotesDataSource.buildPersistentNotes(context)
}