package org.example.app.data

import org.example.app.model.Note

/**
 * PUBLIC_INTERFACE
 * Repository pattern for accessing and updating notes.
 */
class NoteRepository(private val dao: NoteDao) {
    suspend fun getAllNotes(): List<Note> = dao.getAllNotes()
    suspend fun getNoteById(id: String): Note? = dao.getNoteById(id)
    suspend fun searchNotes(query: String): List<Note> = dao.searchNotes(query)
    suspend fun insert(note: Note) = dao.insert(note)
    suspend fun delete(note: Note) = dao.delete(note)
}
