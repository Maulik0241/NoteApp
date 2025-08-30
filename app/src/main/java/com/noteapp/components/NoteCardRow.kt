package com.noteapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.noteapp.model.Note

@Composable
fun NoteCardRow(
    note: Note,
    onNoteClicked: (Note) -> Unit,
    onNoteDelete: (id: String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onNoteClicked(note) } // Navigate to details
                )
            },
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(note.color)
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Title text
                Text(
                    text = note.title,
                    color = Color(0xFFFFFFFF)
                )

                // Delete button
                IconButton(
                    onClick = { onNoteDelete(note.id) }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "Delete Note",
                        tint = Color(0xFFFFFFFF)
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun NoteCardRowPreview() {
    val dummyNote = Note(
        id = "1",
        title = "Sample Note Title",
        description = "This is a short preview of the note content.",
        color = Color(0xFFE0BBE4)
    )

    NoteCardRow(
        note = dummyNote,
        onNoteClicked = { /* Preview only, no navigation */ },
        onNoteDelete = { /* Preview only, no delete */ }
    )
}