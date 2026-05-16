package com.takaobrog.apicomposetask.route

import android.util.Log
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
import com.takaobrog.apicomposetask.screen.task_detail.model.TaskDetailEvent

fun NavGraphBuilder.taskDetailRoute(navController: NavHostController) {
    composable(
        route = "${ScreenRoute.TaskDetail.route}/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType })
    ) {
        val viewModel : TaskDetailViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val isRefreshing by viewModel.isRefreshing.collectAsState()

        TaskDetailScreen(
            state = uiState,
            onEvent = { event ->
                when (event) {
                    TaskDetailEvent.OnRefresh -> viewModel.onRefresh()
                    TaskDetailEvent.OnDeleteConfirmClick -> viewModel.deleteConfirm()
                    is TaskDetailEvent.OnEditTaskEvent -> {
                        Log.d("DEBUG", "OnEditTaskEvent ${event.id}")
                    }
                    TaskDetailEvent.OnBackEvent -> navController.popBackStack()
                }
            },
            isRefreshing = isRefreshing,
        )
    }
}