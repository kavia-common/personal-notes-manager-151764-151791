package org.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import kotlinx.coroutines.*
import androidx.lifecycle.lifecycleScope
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.app.data.NoteDatabase
import org.example.app.data.NoteRepository
import org.example.app.ui.NotesApp
import org.example.app.ui.NotesAppTheme
import org.example.app.ui.NotesViewModel

/**
 * PUBLIC_INTERFACE
 * Main entrypoint Activity for Notes App.
 * Sets up Room and Compose root, ensuring DB init is done off the UI thread for snappy startup.
 */
class MainActivity : ComponentActivity() {
    /**
     * PUBLIC_INTERFACE
     * Main launcher activity for the Notes app.
     * Instantly sets Compose content and initializes ViewModel async.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                MainActivityContent(this)
            }
        }
    }
}

/**
 * Composable root for MainActivity. Makes sure initialization is 100% main-thread non-blocking.
 */
@Composable
private fun MainActivityContent(activity: ComponentActivity) {
    var viewModel by remember { mutableStateOf<NotesViewModel?>(null) }
    // Only kick off DB init when viewModel is null
    LaunchedEffect(Unit) {
        if (viewModel == null) {
            val vm = withContext(Dispatchers.IO) {
                val db = Room.databaseBuilder(
                    activity.applicationContext,
                    NoteDatabase::class.java, "notes.db"
                ).fallbackToDestructiveMigration().build()
                val repository = NoteRepository(db.noteDao())
                NotesViewModel(repository)
            }
            viewModel = vm
        }
    }
    if (viewModel == null) {
        // Minimal Compose-native splash
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        NotesApp(viewModel!!)
    }
}
