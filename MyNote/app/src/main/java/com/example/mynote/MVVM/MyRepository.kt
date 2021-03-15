package com.example.mynote.MVVM

import androidx.lifecycle.LiveData
import com.example.mynote.db.Note
import com.example.mynote.db.NoteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyRepository( val noteDao: NoteDao) {




    fun addTask(note: Note){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.addNotes(note)
        }
    }
    fun getTask():LiveData<List<Note>>{
        return noteDao.getAllNotes()
    }

    fun updateTask(note: Note){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.updateNote(note)
        }
    }
    fun deleteTask(note: Note){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.deleteNote(note)
        }
    }

}