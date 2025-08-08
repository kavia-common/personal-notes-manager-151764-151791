package org.example.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * PUBLIC_INTERFACE
 * A data class representing a Note object.
 * @param id Unique identifier for the note.
 * @param title The title of the note.
 * @param content The main body of the note.
 * @param timestamp The last modified timestamp of the note.
 */
@Entity
data class Note(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val title: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
)
