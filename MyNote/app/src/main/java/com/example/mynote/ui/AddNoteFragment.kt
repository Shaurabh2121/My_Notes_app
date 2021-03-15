package com.example.mynote.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.mynote.MVVM.MyViewModel
import com.example.mynote.R
import com.example.mynote.db.Note
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch


class AddNoteFragment : BaseFragment() {
    lateinit var viewModel: MyViewModel
    private var note: Note? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            note = AddNoteFragmentArgs.fromBundle(it).note
            etTitle.setText(note?.title)
            etNote.setText(note?.note)
        }
        viewModel= ViewModelProviders.of(this).get(MyViewModel::class.java)

        btnCheck.setOnClickListener { view ->
            val noteTitle = etTitle.text.toString().trim()
            val noteBody = etNote.text.toString().trim()
            if (noteTitle.isEmpty()) {
                etTitle.error = "title required"
                etTitle.requestFocus()
                return@setOnClickListener
            }
            if (noteBody.isEmpty()) {
                etNote.error = "note required"
                etNote.requestFocus()
                return@setOnClickListener
            }
            launch {
                context?.let {
                    val mNote = Note(noteTitle, noteBody)
                    if (note == null) {
//                        NoteDatabase.getDatabase(it).getNoteDao().addNotes(mNote)

                         viewModel.insertNote(mNote)
                        Toast.makeText(context, "Notes saved", Toast.LENGTH_LONG).show()
                    } else {
                        mNote.id = note!!.id
//                        NoteDatabase.getDatabase(it).getNoteDao().updateNote(mNote)
                        viewModel.updateNote(mNote)
                        Toast.makeText(context, "Notes updated", Toast.LENGTH_LONG).show()
                    }
                }
            }
            val action = AddNoteFragmentDirections.actionAddNoteFragmentToHomeFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun deleteNote() {
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("You cannot undo this operation")
            setPositiveButton("Yes") { _, _ ->
                launch {
//                    NoteDatabase.getDatabase(context).getNoteDao().deleteNote(note!!)
                    viewModel.deleteNote(note!!)
                    val action = AddNoteFragmentDirections.actionAddNoteFragmentToHomeFragment()
                    view?.let { Navigation.findNavController(it).navigate(action) }
                }
            }
            setNegativeButton("No") { _, _ ->

            }
        }.create().show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> if (note != null) {
                deleteNote()
            } else {
                Toast.makeText(context, "Cannot delete", Toast.LENGTH_LONG).show()

            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }
}

