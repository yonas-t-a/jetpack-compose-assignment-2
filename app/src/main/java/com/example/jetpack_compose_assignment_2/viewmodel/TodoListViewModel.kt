package com.example.jetpack_compose_assignment_2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_assignment_2.data.local.TodoEntity
import com.example.jetpack_compose_assignment_2.data.repository.TodoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TodoListViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<TodoListUiState>(TodoListUiState.Loading)
    val uiState: StateFlow<TodoListUiState> = _uiState

    init {
        viewModelScope.launch {
            repository.getTodos().collect { result ->
                _uiState.value = when {
                    result.isSuccess -> TodoListUiState.Success(result.getOrNull() ?: emptyList())
                    else -> TodoListUiState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
                }
            }
        }
    }
}

sealed class TodoListUiState {
    object Loading : TodoListUiState()
    data class Success(val todos: List<TodoEntity>) : TodoListUiState()
    data class Error(val message: String) : TodoListUiState()
}
