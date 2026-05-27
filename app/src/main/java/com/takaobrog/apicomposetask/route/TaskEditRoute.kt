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
import com.takaobrog.apicomposetask.screen.task_edit.TaskEditScreen
import com.takaobrog.apicomposetask.screen.task_edit.TaskEditViewModel
import com.takaobrog.apicomposetask.screen.task_edit.model.TaskEditEffect
import com.takaobrog.apicomposetask.screen.task_edit.model.TaskEditEvent
import com.takaobrog.component.screen.FrontLayerScreen

fun NavGraphBuilder.taskEditRoute(navController: NavHostController) {
    composable(
        route = "${ScreenRoute.TaskEdit.route}/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType })
    ) {
        val viewModel: TaskEditViewModel = hiltViewModel()
        val formState by viewModel.formState.collectAsState()
        val frontLayerState by viewModel.frontLayerState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    TaskEditEffect.NavigateBack -> navController.popBackStack()
                }
            }
        }

        TaskEditScreen(
            formState = formState, onEvent = { event ->
                when (event) {
                    TaskEditEvent.OnSubmit -> viewModel.onSubmit()

                    is TaskEditEvent.OnValueChangeTitle -> viewModel.inputTitle(title = event.title)

                    is TaskEditEvent.OnValueChangeComment -> viewModel.inputComment(comment = event.comment)

                    is TaskEditEvent.OnValueChangeTargetDate -> viewModel.inputTargetDate(targetDate = event.targetDate)

                    TaskEditEvent.OnBackEvent -> navController.popBackStack()
                }
            }
        )
        FrontLayerScreen(state = frontLayerState, onDismissErrorDialog = viewModel::onDismiss)
    }
}