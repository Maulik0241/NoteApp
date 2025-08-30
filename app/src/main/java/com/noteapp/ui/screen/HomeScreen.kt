package com.noteapp.ui.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.noteapp.R
import com.noteapp.components.EmptyView
import com.noteapp.components.NoteCardRow
import com.noteapp.ui.theme.Primary
import com.noteapp.ui.theme.textColor
import com.noteapp.viewmodel.NoteViewModel

@Composable
fun HomeScreen(context: Context, navController: NavController, noteViewModel: NoteViewModel) {
    val noteList by noteViewModel.notes.observeAsState(arrayListOf())
    Surface(Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Primary)) {
        Scaffold(
            containerColor = Primary,
            topBar = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(top = 50.dp, start = 16.dp, end = 16.dp)) {
                    Text(
                        text = "Notes",
                        color = textColor,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(end = 4.dp).weight(1f)
                    )
                    //search button
                    IconButton(
                        onClick = {
                            navController.navigate("searchNotes")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search Note",
                            tint = textColor
                        )
                    }
                    //info button
                    IconButton(
                        onClick = {
                            //do it later
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Info,
                            contentDescription = "info",
                            tint = Color(0xFFFFFFFF)
                        )
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "note_obj",
                    value = null
                )
                    navController.navigate("addOrEditNote")}) {
                    Icon(Icons.Filled.Add, "Add new item")
                }
            }) { paddingValues ->
            if(noteList.isNotEmpty()){
                LazyColumn(modifier = Modifier.padding(paddingValues)) {
                    items(noteList.size) { index ->
                        val note = noteList[index]
                        NoteCardRow(
                            note = note,
                            onNoteClicked = { selectedNote ->
                                // Navigate to detail screen with selected note
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    key = "note_obj",
                                    value = selectedNote
                                )
                                navController.navigate("NoteDetail")
                            },
                            onNoteDelete = { noteId ->
                                noteViewModel.removeNote(note) // remove from list
                            }
                        )
                    }
                }
            }else {
                EmptyView(imageResId = R.drawable.ic_empty_list, message = "Create your first note !")
            }
        }
    }
}