package com.example.air.androidnotes.view_model

import android.arch.lifecycle.*
import android.os.Bundle
import com.example.air.androidnotes.app.NotesApplication
import com.example.air.androidnotes.data.repository.NotesRepository
import com.example.air.androidnotes.domain.Note
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NotesViewModel : ViewModel(), LifecycleObserver {

    @Inject
    lateinit var notesRepository: NotesRepository

    private var liveCurrencyData: LiveData<List<Note>>? = null

    private val compositeDisposable = CompositeDisposable()

    init {
        initializeDagger()
    }

    fun loadNotesList(): LiveData<List<Note>>? {
        liveCurrencyData = notesRepository.getNotesList()
        return liveCurrencyData
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unSubscribeViewModel() {
        for (disposable in notesRepository.allCompositeDisposable) {
            compositeDisposable.addAll(disposable)
        }
        compositeDisposable.clear()
    }

    private fun initializeDagger() = NotesApplication.appComponent.inject(this)

}