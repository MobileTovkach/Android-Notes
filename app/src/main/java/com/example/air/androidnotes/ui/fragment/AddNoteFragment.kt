package com.example.air.androidnotes.ui.fragment


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.air.androidnotes.R
import com.example.air.androidnotes.app.NotesApplication
import com.example.air.androidnotes.data.room.NotesEntity
import com.example.air.androidnotes.liseners.OnBackPressedListener
import com.example.air.androidnotes.view_model.AddNoteViewModel
import com.example.air.androidnotes.view_model.NotesViewModel
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.fragment_notes.view.*

class AddNoteFragment : Fragment(), OnBackPressedListener {


    private lateinit var addNotesViewModel: AddNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_add_note, container, false)


    override fun onBackPressed() {
        when {
            description_data.text.toString().isNotEmpty() -> addNotesViewModel.addNote(title_data.text.toString(), description_data.text.toString())
            else -> addNotesViewModel.removeNote(NotesEntity(0,"","",0))
        }
        fragmentManager?.popBackStack()
    }

    private fun initViewModel() {
        addNotesViewModel = ViewModelProviders.of(this).get(AddNoteViewModel::class.java)
        addNotesViewModel.let { lifecycle.addObserver(it) }
    }



}

fun newInstanceAddNoteFragment() = AddNoteFragment()

const val TAG: String = "AddNoteFragment"