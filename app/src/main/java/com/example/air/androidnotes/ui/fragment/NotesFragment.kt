package com.example.air.androidnotes.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.air.androidnotes.R
import com.example.air.androidnotes.domain.Note
import com.example.air.androidnotes.view_model.NotesViewModel
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var notesViewModel: NotesViewModel
    private val noteList: ArrayList<Note> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {

    }

    override fun onRefresh() {
        getRefresh(true)
        getListNotes()
    }

    private fun initAdapter(){
        getRefresh(true)
        getListNotes()
    }

    private fun getRefresh(state: Boolean){
        refresh_notes.isRefreshing = state
    }

    private fun initViewModel() {
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        notesViewModel.let { lifecycle.addObserver(it) }
    }

    private fun getListNotes(){
        notesViewModel.loadNotesList()?.observe(this, Observer { listNotes ->
            listNotes!!.forEach {
                noteList.add(Note(it.title, it.description, it.editingDate))
            }
        })
        getRefresh(false)
    }

}

fun newInstance() = NotesFragment()
