@file:Suppress("SameParameterValue")

package com.example.floatingactionbutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.floatingactionbutton.ui.theme.FloatingActionButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FloatingActionButtonTheme {
                Notes()
            }
        }
    }
}

@Composable
fun Notes() {
    val noteList = mutableListOf<Note>()
    val note = ""
    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        Column {
            Header()
            InputNote(note)
            NoteList()
        }
    }
}

@Composable
fun NoteList() {
    LazyColumn {
TODO("Продолжение следует...")
    }
}

@Composable
private fun InputNote(note: String) {
    var noteText by rememberSaveable { mutableStateOf(note) }
    TextField(
        value = noteText,
        onValueChange = { noteText = it },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Header() {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxWidth()
    ) {
        Text(
            text = "Заметки",
            fontSize = 28.sp,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
    }
}

data class Note(val textNote: String)