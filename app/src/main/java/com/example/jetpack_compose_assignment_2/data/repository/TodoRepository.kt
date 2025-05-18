package com.example.jetpack_compose_assignment_2.data.repository

import com.example.jetpack_compose_assignment_2.data.local.TodoDao
import com.example.jetpack_compose_assignment_2.data.local.TodoEntity
import com.example.jetpack_compose_assignment_2.data.remote.TodoApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TodoRepository(
    private val api: TodoApiService,
    private val dao: TodoDao
) {
    fun getTodos(): Flow<Result<List<TodoEntity>>> = flow {
        // Emit cached data first
        val cached = dao.getAllTodos()
        emit(Result.success(cached))

        try {
            val remoteTodos = api.getTodos()
            val entities = remoteTodos.map { TodoEntity(it.id, it.userId, it.title, it.completed) }
            dao.insertTodos(entities)
            emit(Result.success(entities))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    suspend fun getTodoById(id: Int): TodoEntity? = dao.getTodoById(id)
}