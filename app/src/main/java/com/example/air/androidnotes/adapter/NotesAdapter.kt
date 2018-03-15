package com.example.air.androidnotes.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.air.androidnotes.R
import com.example.air.androidnotes.data.room.NotesEntity
import java.text.SimpleDateFormat
import java.util.*

class NotesAdapter: RecyclerView.Adapter<NotesHolder>() {

    private var data: ArrayList<NotesEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        val inflatedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.note_list_item, parent, false)
        return NotesHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.title_note.text = data[position].titleNotes
        holder.desription_note.text = data[position].descriptionNotes
        holder.date_note.text = getStringDate(data[position].editingDate)
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("SimpleDateFormat")
    private fun getStringDate(milisec: Long): String =
            SimpleDateFormat("HH:mm MMM dd, yyyy").format(Date(milisec))

    fun setData(list: ArrayList<NotesEntity>) {
        data.clear()
        data.addAll(list)
    }

}