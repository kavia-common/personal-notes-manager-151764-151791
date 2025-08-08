package org.example.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.example.app.model.Note

/**
 * PUBLIC_INTERFACE
 * Used for both view/edit and create mode.
 * @param note If null, "create" mode, if not null, "edit/view" mode.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    note: Note?,
    onSave: (Note) -> Unit,
    onDelete: ((Note) -> Unit)?,
    onBack: () -> Unit
) {
    var title by remember(note) { mutableStateOf(TextFieldValue(note?.title ?: "")) }
    var content by remember(note) { mutableStateOf(TextFieldValue(note?.content ?: "")) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (note == null) "New Note" else "Edit Note") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Filled.ArrowBack, "back") }
                },
                actions = {
                    if (note != null && onDelete != null) {
                        IconButton(onClick = { showDeleteDialog = true }) {
                            Icon(Icons.Filled.Delete, "delete")
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val trimmedTitle = title.text.trim()
                    val trimmedContent = content.text.trim()
                    if (trimmedTitle.isNotEmpty() || trimmedContent.isNotEmpty()) {
                        onSave(
                            Note(
                                id = note?.id ?: "",
                                title = trimmedTitle,
                                content = trimmedContent,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                    }
                },
                containerColor = Color(0xFFFFCA28),
                contentColor = Color.Black
            ) {
                Icon(Icons.Filled.Check, contentDescription = "Save")
            }
        }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .padding(24.dp)) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Note") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false)
            )
        }
        if (showDeleteDialog && note != null && onDelete != null) {
            ConfirmDialog(
                title = "Delete Note?",
                message = "Are you sure you want to delete this note?",
                onConfirm = {
                    onDelete(note)
                    showDeleteDialog = false
                },
                onDismiss = { showDeleteDialog = false }
            )
        }
    }
}
