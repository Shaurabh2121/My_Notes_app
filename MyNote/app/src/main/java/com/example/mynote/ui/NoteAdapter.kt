package com.example.mynote.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mynote.R
import com.example.mynote.db.Note
import kotlinx.android.synthetic.main.note_item_layout.view.*

class NoteAdapter(val noteList:List<Note>):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){


    inner  class NoteViewHolder(val view: View):RecyclerView.ViewHolder(view)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.note_item_layout,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
     holder.view.tvNoteTitle.text=noteList[position].title
     holder.view.tvNoteDescription.text=noteList[position].note
      holder.view.setOnClickListener {
       val  action =HomeFragmentDirections.ActionAddNotes()
          action.note=noteList[position]
          Navigation.findNavController(it).navigate(action)

      }

    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}