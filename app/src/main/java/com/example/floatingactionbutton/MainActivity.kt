@file:Suppress("SameParameterValue")

package com.example.floatingactionbutton

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
    val noteList = remember { mutableStateListOf<Note>() }
    var note by rememberSaveable { mutableStateOf("") }
    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        Column {
            Header()
            InputNote(note) { newNote ->
                note = newNote
            }
            NoteList(noteList, onDeleteNote = { note ->
                noteList.remove(note)
            })
            AddNote(note) {
                noteList.add(Note(note))
                note = ""
            }
        }
    }
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

@Composable
private fun InputNote(note: String, onNoteChange: (String) -> Unit) {
    TextField(
        value = note,
        onValueChange = onNoteChange,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun NoteList(noteList: MutableList<Note>, onDeleteNote:(Note) -> Unit) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = Modifier
            .height(680.dp)
            .fillMaxWidth()
    ) {
        items(noteList) { note ->
            Box (
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(5.dp)
                    .wrapContentSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    Text(text = note.textNote, fontSize = 22.sp)
                    IconButton(onClick = { onDeleteNote(note) }) {
                        Icon(Icons.Filled.Delete, "deleteNote")
                    }
                }
            }
        }
    }
}

@Composable
fun AddNote(newNote: String, onAddNote: () -> Unit) {
    val context = LocalContext.current
    Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
        FloatingActionButton(
            onClick = {
                if (newNote.isNotBlank()) onAddNote()
                else Toast.makeText(context, "Заметка не введена", Toast.LENGTH_LONG).show()
            }
        ) {
            Icon(Icons.Filled.Add, "addFAB")
        }
    }

}

data class Note(val textNote: String)