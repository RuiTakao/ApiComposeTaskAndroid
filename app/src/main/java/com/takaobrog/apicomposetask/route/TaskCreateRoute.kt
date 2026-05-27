package com.takaobrog.apicomposetask.route

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.takaobrog.apicomposetask.screen.task_create.TaskCreateScreen
import com.takaobrog.apicomposetask.screen.task_create.TaskCreateViewModel
import com.takaobrog.apicomposetask.screen.task_create.model.TaskCreateEffect
import com.takaobrog.apicomposetask.screen.task_create.model.TaskCreateEvent
import com.takaobrog.component.screen.FrontLayerScreen

fun NavGraphBuilder.taskCreateRoute(navController: NavHostController) {
    composable(route = ScreenRoute.TaskCreate.route) {
        val viewModel: TaskCreateViewModel = hiltViewModel()
        val formState by viewModel.formState.collectAsState()
        val frontLayerState by viewModel.frontLayerState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    TaskCreateEffect.NavigateBack -> navController.popBackStack()
                }
            }
        }

        TaskCreateScreen(
            formState = formState,
            onEvent = { event ->
                when (event) {
                    TaskCreateEvent.OnSubmit -> viewModel.onSubmit()
                    is TaskCreateEvent.OnValueChangeTitle ->
                        viewModel.inputTitle(title = event.title)

                    is TaskCreateEvent.OnValueChangeTargetDate ->
                        viewModel.inputTargetDate(targetDate = event.targetDate)

                    is TaskCreateEvent.OnValueChangeComment ->
                        viewModel.inputComment(comment = event.comment)

                    TaskCreateEvent.OnBackEvent -> navController.popBackStack()
                }
            }
        )

        FrontLayerScreen(state = frontLayerState, onDismissErrorDialog = viewModel::dismiss)
    }
}