package com.example.air.androidnotes.app

import com.example.air.androidnotes.view_model.NotesViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RoomModule::class))
@Singleton
interface AppComponent {

    fun inject(notesViewModel: NotesViewModel)

}