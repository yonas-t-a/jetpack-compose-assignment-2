package com.example.jetpack_compose_assignment_2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_assignment_2.data.local.TodoEntity
import com.example.jetpack_compose_assignment_2.data.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoDetailViewModel(private val repository: TodoRepository) : ViewModel() {
    private val _todo = MutableStateFlow<TodoEntity?>(null)
    val todo: StateFlow<TodoEntity?> = _todo

    fun loadTodo(id: Int) {
        viewModelScope.launch {
            _todo.value = repository.getTodoById(id)
        }
    }
}