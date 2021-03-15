package com.example.mynote.MVVM

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynote.db.Note
import com.example.mynote.db.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel( application: Application ) : AndroidViewModel(application) {
    private val repository:MyRepository
    val getTask:LiveData<List<Note>>
    init {
        val dao=NoteDatabase.getDatabase(application).getNoteDao()
        repository= MyRepository(dao)
        getTask=repository.getTask()
    }


    fun deleteNote(note: Note)= viewModelScope.launch (Dispatchers.IO){
        repository.deleteTask(note)
    }
    fun insertNote(note: Note)= viewModelScope.launch (Dispatchers.IO){
        repository.addTask(note)
    }
    fun updateNote(note: Note)= viewModelScope.launch (Dispatchers.IO){
        repository.updateTask(note)
    }

}