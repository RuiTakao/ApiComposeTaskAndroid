package com.takaobrog.apicomposetask.route

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.takaobrog.apicomposetask.screen.TaskListScreen
import com.takaobrog.apicomposetask.screen.TaskListViewModel
import com.takaobrog.apicomposetask.screen.model.TaskListEvent

fun NavGraphBuilder.taskListRoute(navController: NavHostController) {
    composable(route = ScreenRoute.TaskList.route) {
        val viewModel: TaskListViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        TaskListScreen(
            state = state,
            onEvent = { event ->
                when (event) {
                    TaskListEvent.OnRefresh -> viewModel.onRefresh()
                }
            },
            isRefreshing = isRefreshing,
        )
    }
}