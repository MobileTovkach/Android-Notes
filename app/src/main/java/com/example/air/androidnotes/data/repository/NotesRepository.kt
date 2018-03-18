package com.example.air.androidnotes.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.air.androidnotes.data.room.NotesEntity
import com.example.air.androidnotes.data.room.RoomNotesDataSource
import com.example.air.androidnotes.domain.Note
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepository @Inject constructor(
        private val roomNotesDataSource: RoomNotesDataSource): Repository {

    internal val allCompositeDisposable: MutableList<Disposable> = arrayListOf()


    override fun addNote(title: String, description: String) {
        roomNotesDataSource.dao().insertNote(NotesEntity(1, title, description, System.currentTimeMillis()))
    }

    override fun removeNote(note: NotesEntity) {
        roomNotesDataSource.dao().deleteNote(note)
    }

    override fun getNotesList(): LiveData<List<Note>> {
        val roomCurrencyDao = roomNotesDataSource.dao()
        val mutableLiveData = MutableLiveData<List<Note>>()
        val disposable = roomCurrencyDao.getAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    mutableLiveData.value = transform(list)
                }, { t: Throwable? ->
                    t!!.printStackTrace()
                })
        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

    private fun transform(list: List<NotesEntity>): List<Note> {
        val currencyList = ArrayList<Note>()
        list.forEach {
            currencyList.add(Note(it.id, it.titleNotes, it.descriptionNotes, it.editingDate))
        }
        return currencyList
    }

}