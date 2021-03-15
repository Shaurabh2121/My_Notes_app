package com.example.mynote.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend  fun addNotes(note: Note)

    @Query("SELECT * FROM note order by id DESC")
    fun getAllNotes():LiveData<List<Note>>

   @Update
   suspend fun updateNote(note: Note)

   @Delete
   suspend fun deleteNote(note: Note)
}