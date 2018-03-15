package com.example.air.androidnotes.app

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val notesApplication: NotesApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = notesApplication

}