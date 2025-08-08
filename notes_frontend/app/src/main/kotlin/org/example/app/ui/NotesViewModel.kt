package org.example.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.example.app.data.NoteRepository
import org.example.app.model.Note

/**
 * PUBLIC_INTERFACE
 * ViewModel for managing notes list, search, edit, and UI navigation state.
 */
class NotesViewModel(private val repository: NoteRepository) : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedNote = MutableStateFlow<Note?>(null)
    val selectedNote: StateFlow<Note?> = _selectedNote.asStateFlow()

    fun loadNotes() {
        viewModelScope.launch {
            _notes.value = repository.getAllNotes()
        }
    }

    fun search(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            _notes.value = if (query.isBlank()) repository.getAllNotes()
            else repository.searchNotes(query)
        }
    }

    fun selectNote(note: Note?) {
        _selectedNote.value = note
    }

    fun addOrUpdateNote(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
            loadNotes()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.delete(note)
            loadNotes()
        }
    }
}
