package org.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import org.example.app.data.NoteDatabase
import org.example.app.data.NoteRepository
import org.example.app.ui.NotesApp
import org.example.app.ui.NotesAppTheme
import org.example.app.ui.NotesViewModel

/**
 * PUBLIC_INTERFACE
 * Main entrypoint Activity for Notes App. Sets up Room and the Compose root.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DI: Provide Room database and repository
        val db = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java, "notes.db"
        ).fallbackToDestructiveMigration().build()
        val repository = NoteRepository(db.noteDao())
        val viewModel = NotesViewModel(repository)

        setContent {
            NotesAppTheme {
                NotesApp(viewModel)
            }
        }
    }
}
