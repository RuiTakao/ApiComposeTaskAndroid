package com.takaobrog.apicomposetask.route

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.takaobrog.apicomposetask.screen.task_list.TaskListScreen
import com.takaobrog.apicomposetask.screen.task_list.TaskListViewModel
import com.takaobrog.apicomposetask.screen.task_list.model.TaskListEvent
import com.takaobrog.component.screen.FrontLayerScreen

fun NavGraphBuilder.taskListRoute(navController: NavHostController) {
    composable(route = ScreenRoute.TaskList.route) {
        val viewModel: TaskListViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsState()
        val frontLayerState by viewModel.frontLayerState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        TaskListScreen(
            state = state,
            onEvent = { event ->
                when (event) {
                    TaskListEvent.OnRefresh -> viewModel.onRefresh()

                    TaskListEvent.OnClickFab ->
                        navController.navigate(route = ScreenRoute.TaskCreate.route)

                    is TaskListEvent.OnClickItem ->
                        navController.navigate(route = "${ScreenRoute.TaskDetail.route}/${event.id}")
                }
            },
            isRefreshing = isRefreshing,
        )

        FrontLayerScreen(state = frontLayerState, onDismissErrorDialog = viewModel::onDismiss)
    }
}