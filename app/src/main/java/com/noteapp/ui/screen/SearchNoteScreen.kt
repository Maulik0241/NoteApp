package com.noteapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.noteapp.components.CustomSearchBar
import com.noteapp.ui.theme.Primary
import com.noteapp.viewmodel.NoteViewModel

@Composable
fun SearchNoteScreen(noteViewModel: NoteViewModel) {
    Surface(Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Scaffold(
            containerColor = Primary
        ) { paddingValues->
            CustomSearchBar(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
                    .padding(16.dp),
                onSearch = { query -> noteViewModel.searchNote(query) }
            )
        }
    }
}

@Preview
@Composable
fun SearchNotePreview() {
    
}