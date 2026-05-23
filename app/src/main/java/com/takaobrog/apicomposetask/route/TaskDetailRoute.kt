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
import com.takaobrog.component.component.dialog.ErrorDialog
import com.takaobrog.component.component.dialog.OkCancelDialog
import com.takaobrog.component.model.FrontLayerState

fun NavGraphBuilder.taskDetailRoute(navController: NavHostController) {
    composable(
        route = "${ScreenRoute.TaskDetail.route}/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType })
    ) {
        val viewModel: TaskDetailViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val frontLayerState by viewModel.frontLayerState.collectAsState()
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

        when (frontLayerState) {
            FrontLayerState.Idle -> null
            FrontLayerState.Loading -> null
            is FrontLayerState.Confirm -> OkCancelDialog(
                onConfirm = { viewModel.onDelete() },
                onDismiss = { viewModel.onDismiss() },
                title = (frontLayerState as FrontLayerState.Confirm).title,
            )

            is FrontLayerState.Error -> ErrorDialog(
                state = (frontLayerState as FrontLayerState.Error).error,
                onDismiss = { viewModel.onDismiss() },
            )
        }
    }
}