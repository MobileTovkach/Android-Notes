package com.example.air.androidnotes.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.air.androidnotes.R

class NotesHolder  internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){

    var title_note = itemView.findViewById<TextView>(R.id.title_text)
    var desription_note = itemView.findViewById<TextView>(R.id.description_text)
    var date_note = itemView.findViewById<TextView>(R.id.edit_data)

}