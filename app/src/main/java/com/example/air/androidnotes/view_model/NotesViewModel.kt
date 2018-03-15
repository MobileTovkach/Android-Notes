package com.example.air.androidnotes.view_model

import android.arch.lifecycle.*
import android.util.Log
import com.example.air.androidnotes.data.repository.NotesRepository
import com.example.air.androidnotes.domain.Note
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NotesViewModel : ViewModel(), LifecycleObserver {

    @Inject
    lateinit var notesRepository: NotesRepository

    private var liveCurrencyData: LiveData<List<Note>>? = null

    private val compositeDisposable = CompositeDisposable()

    private fun isRoomEmpty(currenciesTotal: Int) = currenciesTotal == 0

    fun loadNotesList(): LiveData<List<Note>>? {
        if (liveCurrencyData == null) {
            liveCurrencyData = MutableLiveData<List<Note>>()
            liveCurrencyData = notesRepository.getNotesList()
        }
        return liveCurrencyData
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unSubscribeViewModel() {
        for (disposable in notesRepository.allCompositeDisposable) {
            compositeDisposable.addAll(disposable)
        }
        compositeDisposable.clear()
    }

}