package org.example.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.example.app.model.Note

/**
 * PUBLIC_INTERFACE
 * Home screen: shows all notes with search and navigation.
 */
@Composable
fun NotesListScreen(
    notes: List<Note>,
    searchQuery: String,
    onSearch: (String) -> Unit,
    onNoteClick: (Note) -> Unit,
    onAddClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = Color(0xFF1976d2),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add note")
            }
        }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .fillMaxSize()
            .padding(16.dp)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearch,
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                label = { Text("Search", color = Color(0xFF1976d2)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1976d2),
                    unfocusedBorderColor = Color(0xFF424242)
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(notes) { note ->
                    Surface(
                        color = Color(0xFFF6F7F9),
                        shadowElevation = 0.dp,
                        tonalElevation = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { onNoteClick(note) }
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = note.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFF1976d2)
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = if (note.content.length > 64) note.content.substring(0, 61) + "..." else note.content,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF424242)
                            )
                        }
                    }
                }
            }

            if (notes.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "No notes yet.", color = Color(0xAA424242))
                }
            }
        }
    }
}
