    package com.noteapp.viewmodel

    import androidx.compose.ui.graphics.Color
    import androidx.lifecycle.LiveData
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import com.noteapp.model.Note

    class NoteViewModel : ViewModel() {
        private val _notes = MutableLiveData<ArrayList<Note>>().apply {
            value = arrayListOf(
                Note("1", "Shopping List", "", Color(0xFFFFF176)),
                Note("2", "Workout Plan", "", Color(0xFF80DEEA)),
                Note("3", "Meeting Notes", "", Color(0xFFE1BEE7))
            )
        }
        val notes: LiveData<ArrayList<Note>> = _notes
        private val _filteredNotes = MutableLiveData<ArrayList<Note>>().apply {
            value = arrayListOf(
                Note("1", "Shopping List", "", Color(0xFFFFF176)),
                Note("2", "Workout Plan", "", Color(0xFF80DEEA)),
                Note("3", "Meeting Notes", "", Color(0xFFE1BEE7))
            )
        }
        val filteredNotes: LiveData<ArrayList<Note>> = _filteredNotes
        /**
         * Add a new note to the list
         */
        fun addNote(note: Note) {
            val currentList = _notes.value ?: ArrayList()
            currentList.add(note)
            _notes.value = currentList
        }
        /**
         * Remove a note from the list
         */
        fun removeNote(note: Note) {
            val currentList = _notes.value ?: ArrayList()
            val newList = ArrayList(currentList) // create new list instance
            newList.remove(note)
            _notes.value = newList
        }

        /**
         * Update a note in the list
         */
        fun updateNote(note: Note) {
            val currentList = _notes.value ?: ArrayList()
            val index = currentList.indexOfFirst { it.id == note.id }
            if (index != -1) {
                currentList[index] = note
                _notes.value = currentList
            }
        }

        /**
         * search a note in the list
         */
        fun searchNote(query: String){
            val currentList = _notes.value ?: ArrayList()
            if (query.isEmpty()) {
                _filteredNotes.value = currentList
            } else {
                val result = currentList.filter {
                    it.title.contains(query, ignoreCase = true) ||
                            it.title.contains(query, ignoreCase = true)
                }
                _filteredNotes.value = ArrayList(result)
            }
        }
    }