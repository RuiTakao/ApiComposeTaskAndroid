package com.takaobrog.apicomposetask.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.takaobrog.apicomposetask.screen.task_create.TaskCreateScreen

fun NavGraphBuilder.taskCreateRoute(navController: NavHostController) {
    composable(route = ScreenRoute.TaskCreate.route) {
        TaskCreateScreen()
    }
}