package com.takaobrog.apicomposetask.route

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.takaobrog.apicomposetask.screen.task_detail.TaskDetailScreen
import com.takaobrog.apicomposetask.screen.task_detail.TaskDetailViewModel
import com.takaobrog.apicomposetask.screen.task_detail.model.TaskDetailEffect
import com.takaobrog.apicomposetask.screen.task_detail.model.TaskDetailEvent
import com.takaobrog.component.screen.DialogScreen

fun NavGraphBuilder.taskDetailRoute(navController: NavHostController) {
    composable(
        route = "${ScreenRoute.TaskDetail.route}/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType })
    ) {
        val viewModel: TaskDetailViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val dialogState by viewModel.dialogState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    TaskDetailEffect.OnBackEvent -> navController.popBackStack()
                }
            }
        }

        TaskDetailScreen(
            state = uiState,
            onEvent = { event ->
                when (event) {
                    TaskDetailEvent.OnRefresh -> viewModel.onRefresh()
                    is TaskDetailEvent.OnDismiss -> navController.popBackStack()
                    is TaskDetailEvent.OnDeleteConfirmClick -> viewModel.deleteConfirm(title = event.title)
                    is TaskDetailEvent.OnEditTaskEvent -> navController.navigate(route = "${ScreenRoute.TaskEdit.route}/${event.id}")

                    TaskDetailEvent.OnBackEvent -> navController.popBackStack()
                }
            },
            isRefreshing = isRefreshing,
        )

        DialogScreen(
            state = dialogState,
            onDismiss = { viewModel.onDismiss() },
            onConfirm = { viewModel.onDelete() },
        )
    }
}