package com.takaobrog.apicomposetask.route

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.takaobrog.apicomposetask.screen.task_create.TaskCreateScreen
import com.takaobrog.apicomposetask.screen.task_create.TaskCreateViewModel
import com.takaobrog.apicomposetask.screen.task_create.model.TaskCreateEffect
import com.takaobrog.apicomposetask.screen.task_create.model.TaskCreateEvent
import com.takaobrog.component.R
import com.takaobrog.component.model.SendingState
import com.takaobrog.component.screen.ErrorScreen
import com.takaobrog.component.screen.LoadingScreen

fun NavGraphBuilder.taskCreateRoute(navController: NavHostController) {
    composable(route = ScreenRoute.TaskCreate.route) {
        val viewModel: TaskCreateViewModel = hiltViewModel()
        val formState by viewModel.formState.collectAsState()
        val sendingState by viewModel.sendingState.collectAsState()

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

        when (sendingState) {
            SendingState.Idle -> null
            SendingState.Sending -> LoadingScreen(
                color = colorResource(id = R.color.reloading_indicator_color),
                alpha = 0.6f,
            )

            is SendingState.Error -> ErrorScreen(
                state = (sendingState as SendingState.Error).error,
                onDismiss = { viewModel.dismiss() }
            )
        }
    }
}