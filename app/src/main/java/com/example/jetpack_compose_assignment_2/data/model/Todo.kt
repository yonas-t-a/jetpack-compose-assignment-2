package com.example.jetpack_compose_assignment_2.data.model

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)