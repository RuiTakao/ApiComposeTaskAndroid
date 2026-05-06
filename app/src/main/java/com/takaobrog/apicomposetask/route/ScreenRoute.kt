package com.takaobrog.apicomposetask.route

sealed class ScreenRoute(val route: String) {
    object TaskList: ScreenRoute("task_list")
    object TaskCreate: ScreenRoute("task_create")
    object TaskDetail: ScreenRoute("task_detail")
}