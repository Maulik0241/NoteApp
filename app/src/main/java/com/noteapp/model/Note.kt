package com.noteapp.model

import androidx.compose.ui.graphics.Color
import java.util.Date
import java.util.UUID
data class Note(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val color: Color,
    val date: Date = Date()
)