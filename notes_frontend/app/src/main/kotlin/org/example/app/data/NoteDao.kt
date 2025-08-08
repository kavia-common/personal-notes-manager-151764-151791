package org.example.app.data

import androidx.room.*
import org.example.app.model.Note

/**
 * PUBLIC_INTERFACE
 * DAO for accessing Note data in Room.
 */
@Dao
interface NoteDao {
    @Query("SELECT * FROM Note ORDER BY timestamp DESC")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM Note WHERE id = :id LIMIT 1")
    suspend fun getNoteById(id: String): Note?

    @Query("SELECT * FROM Note WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%' ORDER BY timestamp DESC")
    suspend fun searchNotes(query: String): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)
}
