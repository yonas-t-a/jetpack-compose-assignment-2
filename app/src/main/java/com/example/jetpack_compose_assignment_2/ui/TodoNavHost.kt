package com.example.jetpack_compose_assignment_2.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.jetpack_compose_assignment_2.viewmodel.TodoListViewModel
import com.example.jetpack_compose_assignment_2.viewmodel.TodoDetailViewModel

@Composable
fun TodoNavHost(
    listViewModel: TodoListViewModel,
    detailViewModel: TodoDetailViewModel
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "list") {
        composable("list") {
            TodoListScreen(
                viewModel = listViewModel,
                onTodoClick = { id -> navController.navigate("detail/$id") }
            )
        }
        composable(
            "detail/{todoId}",
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId") ?: 0
            detailViewModel.loadTodo(todoId)
            TodoDetailScreen(viewModel = detailViewModel, onBack = { navController.popBackStack() })
        }
    }
}
