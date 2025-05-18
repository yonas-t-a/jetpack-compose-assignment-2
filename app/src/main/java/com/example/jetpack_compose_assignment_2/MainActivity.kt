package com.example.jetpack_compose_assignment_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.jetpack_compose_assignment_2.data.local.TodoDatabase
import com.example.jetpack_compose_assignment_2.data.remote.RetrofitInstance
import com.example.jetpack_compose_assignment_2.data.repository.TodoRepository
import com.example.jetpack_compose_assignment_2.ui.TodoNavHost
import com.example.jetpack_compose_assignment_2.ui.theme.Jetpackcomposeassignment2Theme
import com.example.jetpack_compose_assignment_2.viewmodel.TodoDetailViewModel
import com.example.jetpack_compose_assignment_2.viewmodel.TodoListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java, "todo-db"
        ).build()

        val repository = TodoRepository(RetrofitInstance.api, db.todoDao())
        val listViewModel = TodoListViewModel(repository)
        val detailViewModel = TodoDetailViewModel(repository)

        setContent {
            Jetpackcomposeassignment2Theme {
                TodoNavHost(listViewModel, detailViewModel)
            }
        }
    }
}

