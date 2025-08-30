package com.noteapp.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import com.noteapp.viewmodel.NoteViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.noteapp.ui.screen.AddOrEditNoteScreen
import com.noteapp.ui.screen.HomeScreen
import com.noteapp.ui.screen.NoteDetailScreen
import com.noteapp.ui.screen.SearchNoteScreen

@Composable
fun AppNavigationHost(context: Context,noteViewModel: NoteViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(context,navController,noteViewModel) }
        composable("addOrEditNote") { AddOrEditNoteScreen(navController,noteViewModel) }
        composable("noteDetail") { NoteDetailScreen(navController) }
        composable("searchNotes") { SearchNoteScreen(noteViewModel = noteViewModel) }
    }
}