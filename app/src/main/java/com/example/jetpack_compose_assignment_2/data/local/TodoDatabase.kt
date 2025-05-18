package com.example.jetpack_compose_assignment_2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpack_compose_assignment_2.data.local.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}