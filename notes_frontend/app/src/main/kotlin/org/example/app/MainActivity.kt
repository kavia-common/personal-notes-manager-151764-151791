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
    private var viewModel: NotesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // BOOTSTRAP: Show splash while initializing DB/repo/ViewModel off main thread
        // Use classic Android state; only call setContent when ready
        setContent {
            NotesAppTheme {
                // Show loading while viewModel is null
                if (viewModel == null) {
                    // Trigger initialization once (can't use LaunchedEffect reliably here)
                    AndroidViewBindingSplash { // custom function defined below
                        initViewModelInBackground()
                    }
                } else {
                    NotesApp(viewModel!!)
                }
            }
        }
    }

    // Custom function to start loading initialization using Android Handler
    @Composable
    private fun AndroidViewBindingSplash(launchInit: () -> Unit) {
        LaunchedEffect(Unit) { launchInit() }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    // Run DB and repository initialization on background thread, update viewModel on UI thread
    private fun initViewModelInBackground() {
        // Only run once
        if (viewModel != null) return
        CoroutineScope(Dispatchers.IO).launch {
            val db = Room.databaseBuilder(
                applicationContext,
                NoteDatabase::class.java, "notes.db"
            ).fallbackToDestructiveMigration().build()
            val repository = NoteRepository(db.noteDao())
            val vm = NotesViewModel(repository)
            withContext(Dispatchers.Main) {
                viewModel = vm
                // force a recomposition, setContent already set
                // In Compose Activity, calling setContent again will recompose.
                // Viewing viewModel as classic var triggers compose recomposition
                // already because it's referenced in the setContent scope.
            }
        }
    }
}
