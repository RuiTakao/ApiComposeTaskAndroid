package com.takaobrog.apicomposetask.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.taskDetailRoute(navController: NavHostController) {
    composable(
        route = "${ScreenRoute.TaskDetail.route}/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType })
    ) {

    }
}