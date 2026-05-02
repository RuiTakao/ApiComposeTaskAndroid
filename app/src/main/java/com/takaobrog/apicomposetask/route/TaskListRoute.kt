package com.takaobrog.apicomposetask.route

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.takaobrog.apicomposetask.screen.TaskListScreen
import com.takaobrog.apicomposetask.screen.TaskListViewModel

fun NavGraphBuilder.taskListRoute(navController: NavHostController) {
    composable(route = ScreenRoute.TaskList.route) {
        val viewModel: TaskListViewModel = hiltViewModel()
        TaskListScreen(onClick = viewModel::test)
    }
}