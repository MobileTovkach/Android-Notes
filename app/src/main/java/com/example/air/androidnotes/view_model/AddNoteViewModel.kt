package com.example.air.androidnotes.view_model

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import com.example.air.androidnotes.data.repository.NotesRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AddNoteViewModel : ViewModel(), LifecycleObserver {

    @Inject
    lateinit var notesRepository: NotesRepository

    private val compositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unSubscribeViewModel() {
        for (disposable in notesRepository.allCompositeDisposable) {
            compositeDisposable.addAll(disposable)
        }
        compositeDisposable.clear()
    }


}