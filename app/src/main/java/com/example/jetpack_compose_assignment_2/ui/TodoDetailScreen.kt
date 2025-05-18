package com.example.jetpack_compose_assignment_2.ui

import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_assignment_2.viewmodel.TodoDetailViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment

@Composable
fun TodoDetailScreen(
    viewModel: TodoDetailViewModel,
    onBack: () -> Unit
) {
    val todo by viewModel.todo.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo Detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        todo?.let {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text("Title: ${it.title}", style = MaterialTheme.typography.h6)
                Spacer(Modifier.height(8.dp))
                Text("Completed: ${it.completed}")
                Spacer(Modifier.height(8.dp))
                Text("User ID: ${it.userId}")
                Spacer(Modifier.height(8.dp))
                Text("Todo ID: ${it.id}")
            }
        } ?: Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading...")
        }
    }
}
