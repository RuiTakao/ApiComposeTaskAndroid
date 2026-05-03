package com.takaobrog.apicomposetask.route

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.takaobrog.apicomposetask.R
import com.takaobrog.apicomposetask.component.OkDialog
import com.takaobrog.apicomposetask.screen.TaskListScreen
import com.takaobrog.apicomposetask.screen.TaskListViewModel
import com.takaobrog.apicomposetask.screen.model.TaskListEvent
import com.takaobrog.apicomposetask.screen.model.TaskListUiState

fun NavGraphBuilder.taskListRoute(navController: NavHostController) {
    composable(route = ScreenRoute.TaskList.route) {
        val viewModel: TaskListViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()
        val isShowDialog by viewModel.isShowDialog.collectAsState()

        TaskListScreen(
            state = state,
            onEvent = { event ->
                when (event) {
                    TaskListEvent.OnRefresh -> viewModel.onRefresh()
                }
            },
            isRefreshing = isRefreshing,
        )

        if (isShowDialog) {
            OkDialog(
                onDismiss = viewModel::onDismiss,
                title = (state as TaskListUiState.Error).message ?: "",
                titleColor = colorResource(id = R.color.danger_color),
            )
        }
    }
}