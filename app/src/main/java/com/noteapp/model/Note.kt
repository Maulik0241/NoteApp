package com.noteapp.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import java.util.Date
import java.util.UUID

@Parcelize
data class Note(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val colorLong: Long,
    val date: Long = Date().time
): Parcelable{
    val color: Color
        get() = Color(colorLong) // convert back to Color when needed

    val noteDate: Date
        get() = Date(date)
}