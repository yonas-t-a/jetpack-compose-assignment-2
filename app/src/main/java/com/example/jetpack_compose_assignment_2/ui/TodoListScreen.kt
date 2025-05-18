package com.example.jetpack_compose_assignment_2.ui

import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_assignment_2.viewmodel.TodoListUiState
import com.example.jetpack_compose_assignment_2.viewmodel.TodoListViewModel


@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel,
    onTodoClick: (Int) -> Unit,

) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is TodoListUiState.Loading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is TodoListUiState.Error -> {
            val message = (uiState as TodoListUiState.Error).message
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: $message")
            }
        }
        is TodoListUiState.Success -> {
            val todos = (uiState as TodoListUiState.Success).todos
                LazyColumn {
                    items(todos) { todo ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .background(MaterialTheme.colors.surface)
                                .clickable { onTodoClick(todo.id) }
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(todo.title, modifier = Modifier.weight(1f))
                                Checkbox(checked = todo.completed, onCheckedChange = null)
                            }
                        }
                    }
                }
        }
    }
}
