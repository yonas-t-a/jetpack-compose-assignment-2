package com.example.jetpack_compose_assignment_2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpack_compose_assignment_2.data.local.TodoEntity

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    suspend fun getAllTodos(): List<TodoEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertTodos(todos: List<TodoEntity>)

    @Query("SELECT * FROM todos WHERE id = :id")
    suspend fun getTodoById(id: Int): TodoEntity?
}