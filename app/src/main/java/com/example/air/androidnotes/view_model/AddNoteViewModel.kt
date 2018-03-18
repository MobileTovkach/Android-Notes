package com.example.air.androidnotes.view_model

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.air.androidnotes.app.NotesApplication
import com.example.air.androidnotes.data.repository.NotesRepository
import com.example.air.androidnotes.data.room.NotesEntity
import com.example.air.androidnotes.domain.Note
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddNoteViewModel : ViewModel(), LifecycleObserver {

    @Inject
    lateinit var notesRepository: NotesRepository

    private val compositeDisposable = CompositeDisposable()

    init {
        initializeDagger()
    }

    fun addNote(title: String, description: String){
        Completable.fromAction { notesRepository.addNote(title, description) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(@NonNull d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onComplete() {
                        Log.i(NotesRepository::class.java.simpleName, "DataSource has been Populated")

                    }

                    override fun onError(@NonNull e: Throwable) {
                        e.printStackTrace()
                        Log.e(NotesRepository::class.java.simpleName, "DataSource hasn't been Populated")
                    }
                })
    }

    fun removeNote(note: NotesEntity){
        notesRepository.removeNote(note)
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