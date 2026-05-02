package com.takaobrog.apicomposetask.route

sealed class ScreenRoute(val route: String) {
    object TaskList: ScreenRoute("task_list")
}