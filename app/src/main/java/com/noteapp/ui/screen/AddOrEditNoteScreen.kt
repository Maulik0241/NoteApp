package com.noteapp.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.noteapp.model.Note
import com.noteapp.ui.theme.Primary
import com.noteapp.viewmodel.NoteViewModel
import com.noteapp.R

@Composable
fun AddOrEditNoteScreen(
    navController: NavController,
    noteViewModel: NoteViewModel
) {
    val noteObj = navController.previousBackStackEntry
        ?.savedStateHandle
        ?.get<Note>("note_obj")

    var title by remember { mutableStateOf(noteObj?.title ?: "") }
    var description by remember { mutableStateOf(noteObj?.description ?: "") }

    var showDialog by remember { mutableStateOf(false) }
    var titleError by remember { mutableStateOf(false) }
    var descriptionError by remember { mutableStateOf(false) }


    val noteColors = listOf(
        0xFFFFF176,
        0xFF80DEEA,
        0xFFE1BEE7,
        0xFFA5D6A7,
        0xFFFFAB91
    )

    val hasUnsavedChanges = if(noteObj != null){
        title != noteObj.title || description != noteObj.description
    }else{
        false
    }

    fun saveNoteAndNavigate() {

        // Reset errors
        titleError = false
        descriptionError = false

        if (title.isEmpty() || description.isEmpty()) {
            // Show error
            if (title.isEmpty()) titleError = true
            if (description.isEmpty()) descriptionError = true
            return
        }

        if (noteObj != null) {
            // Update existing note
            val updatedNote = noteObj.copy(
                title = title,
                description = description,
                colorLong = noteObj.colorLong, // keep existing color
                date = noteObj.date // keep original date
            )
            noteViewModel.updateNote(updatedNote)
        } else if (title.isNotEmpty() || description.isNotEmpty()) {
            // Add new note with random color
            val randomColorLong = noteColors.random().toLong()
            val newNote = Note(
                title = title,
                description = description,
                colorLong = randomColorLong
            )
            noteViewModel.addNote(newNote)
        }
        navController.popBackStack()
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            text = { Text("Save changes ?") },
            confirmButton = {
                TextButton(onClick = {
                    saveNoteAndNavigate()
                    showDialog = false
                }) { Text("Save") }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                    navController.popBackStack()
                }) { Text("Discard") }
            }
        )
    }

    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, start = 16.dp, end = 16.dp)
            ) {
                IconButton(onClick = {
                    if (hasUnsavedChanges) {
                        showDialog = true
                    } else {
                        navController.popBackStack()
                    }
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { saveNoteAndNavigate() }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.ic_save),
                        contentDescription = "Save",
                        tint = Color.White
                    )
                }
            }
        },
        containerColor = Primary
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    if (titleError) titleError = false
                } ,
                label = { Text("Title") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                textStyle = TextStyle(color = Color.White),
                isError = titleError,

                )
            if (titleError) {
                Text(
                    text = "Please enter your title",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = description,
                onValueChange = {
                    description = it
                    if (descriptionError) descriptionError = false },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                textStyle = TextStyle(color = Color.White),
                isError = descriptionError,
            )
            if (descriptionError) {
                Text(
                    text = "Please enter you description",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

