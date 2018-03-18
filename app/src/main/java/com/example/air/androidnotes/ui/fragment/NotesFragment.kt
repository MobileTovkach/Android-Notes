package com.example.air.androidnotes.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.example.air.androidnotes.R
import com.example.air.androidnotes.adapter.NotesAdapter
import com.example.air.androidnotes.adapter.NotesHolder
import com.example.air.androidnotes.domain.Note
import com.example.air.androidnotes.view_model.NotesViewModel
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.android.synthetic.main.fragment_notes.view.*

class NotesFragment : Fragment(),
        SwipeRefreshLayout.OnRefreshListener,
        NotesHolder.CallbackData{

    private lateinit var notesViewModel: NotesViewModel
    private lateinit var noteList: ArrayList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_notes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        view.refresh_notes.setOnRefreshListener(this)
        view.refresh_notes.isRefreshing = true
        getListNotes()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_new_note -> {
                fragmentManager?.beginTransaction()
                        ?.replace(R.id.content, newInstanceAddNoteFragment())
                        ?.addToBackStack(this.tag)
                        ?.commit()
                super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun openEditNoteFragment(position: Int) {
        val bundle = Bundle()
        noteList[position].idNote?.let { bundle.putLong("id", it) }
        bundle.putString("title", noteList[position].title)
        bundle.putString("description", noteList[position].description)
        noteList[position].editingDate?.let { bundle.putLong("time", it) }

        val fragment = newInstanceAddNoteFragment()
        fragment.arguments = bundle

        fragmentManager?.beginTransaction()
                ?.replace(R.id.content, fragment)
                ?.addToBackStack(this.tag)
                ?.commit()
    }

    private fun initRecyclerView() {
        val notesAdapter = NotesAdapter(this)
        notesAdapter.setData(noteList)
        list_notes.adapter = notesAdapter
        list_notes.layoutManager = LinearLayoutManager(context)
    }

    override fun onRefresh() {
        refresh_notes.isRefreshing = true
        getListNotes()
    }

    private fun initViewModel() {
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        notesViewModel.let { lifecycle.addObserver(it) }
    }

    private fun getListNotes(){
        notesViewModel.loadNotesList()?.observe(this, Observer { listNotes ->
            noteList = ArrayList()
            listNotes!!.forEach {
                noteList.add(Note(it.idNote, it.title, it.description, it.editingDate))
            }
            refresh_notes.isRefreshing = false
            if(noteList.isEmpty()){
                noNoteList()
            } else {
                initRecyclerView()
            }
        })
    }

    private fun noNoteList() {
        no_notes.visibility = View.VISIBLE
        list_notes.visibility = View.GONE
    }

}

fun newInstanceNoteFragment() = NotesFragment()
