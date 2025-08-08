package org.example.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import org.example.app.model.Note

/**
 * PUBLIC_INTERFACE
 * Room Database for persisting notes offline.
 */
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
