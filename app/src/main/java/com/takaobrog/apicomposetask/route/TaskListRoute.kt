package com.takaobrog.apicomposetask.route

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.takaobrog.apicomposetask.screen.task_list.TaskListScreen
import com.takaobrog.apicomposetask.screen.task_list.TaskListViewModel
import com.takaobrog.apicomposetask.screen.task_list.model.TaskListEvent
import com.takaobrog.component.model.ErrorState
import com.takaobrog.component.screen.ReloadingScreen

fun NavGraphBuilder.taskListRoute(navController: NavHostController) {
    composable(route = ScreenRoute.TaskList.route) {
        val activity = LocalActivity.current
        val viewModel: TaskListViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsState()
        val reloadState by viewModel.reloadState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        TaskListScreen(
            state = state,
            onEvent = { event ->
                when (event) {
                    TaskListEvent.OnRefresh -> viewModel.onRefresh()
                    is TaskListEvent.OnDismiss -> {
                        when (event.error) {
                            ErrorState.NetworkError -> viewModel.onDismiss()
                            is ErrorState.SystemError -> activity?.finish()
                        }
                    }

                    TaskListEvent.OnClickFab ->
                        navController.navigate(route = ScreenRoute.TaskCreate.route)

                    is TaskListEvent.OnClickItem ->
                        navController.navigate(route = "${ScreenRoute.TaskDetail.route}/${event.id}")
                }
            },
            isRefreshing = isRefreshing,
        )

        ReloadingScreen(state = reloadState, onDismissError = viewModel::onDismiss)
    }
}