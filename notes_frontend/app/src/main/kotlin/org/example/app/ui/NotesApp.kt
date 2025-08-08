package org.example.app.ui

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.navigation.compose.*
import org.example.app.model.Note

/**
 * PUBLIC_INTERFACE
 * Handles the navigation for the minimal notes app.
 */
@Composable
fun NotesApp(viewModel: NotesViewModel) {
    val notes by viewModel.notes.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedNote by viewModel.selectedNote.collectAsState()

    val navController = rememberNavController()

    // Load all notes on launch
    LaunchedEffect(Unit) { viewModel.loadNotes() }

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            NotesListScreen(
                notes = notes,
                searchQuery = searchQuery,
                onSearch = { viewModel.search(it) },
                onNoteClick = {
                    viewModel.selectNote(it)
                    navController.navigate("detail")
                },
                onAddClick = {
                    viewModel.selectNote(null)
                    navController.navigate("detail")
                }
            )
        }
        composable("detail") {
            NoteDetailScreen(
                note = selectedNote,
                onSave = { note ->
                    // If editing, keep id, if creating, generate id
                    val actualNote = note.copy(
                        id = note.id.ifBlank { java.util.UUID.randomUUID().toString() },
                        timestamp = System.currentTimeMillis()
                    )
                    viewModel.addOrUpdateNote(actualNote)
                    navController.popBackStack()
                },
                onDelete = if (selectedNote != null) { { toDelete ->
                    viewModel.deleteNote(toDelete)
                    navController.popBackStack()
                } } else null,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
