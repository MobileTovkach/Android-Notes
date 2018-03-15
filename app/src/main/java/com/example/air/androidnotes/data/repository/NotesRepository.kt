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

    private val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

    override fun getTotalNotes() = roomNotesDataSource.currencyDao().getNotesTotal()

    override fun addNote(title: String, description: String) {
        roomNotesDataSource.currencyDao().insertNote(NotesEntity(0, title, description))
    }

    override fun removeNote(note: NotesEntity) {
        roomNotesDataSource.currencyDao().deleteNote(note)
    }

    override fun getNotesList(): LiveData<List<Note>> {
        val roomCurrencyDao = roomNotesDataSource.currencyDao()
        val mutableLiveData = MutableLiveData<List<Note>>()
        val disposable = roomCurrencyDao.getAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ currencyList ->
                    mutableLiveData.value = transform(currencyList)
                }, { t: Throwable? -> t!!.printStackTrace() })
        allCompositeDisposable.add(disposable)
        return mutableLiveData
    }

    private fun transform(currencies: List<NotesEntity>): List<Note> {
        val currencyList = ArrayList<Note>()
        currencies.forEach {
            currencyList.add(Note(it.titleNotes, it.descriptionNotes))
        }
        return currencyList
    }

}