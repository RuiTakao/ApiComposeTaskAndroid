package com.takaobrog.apicomposetask.screen.task_list.model

sealed class TaskListEffect {
    object Reload : TaskListEffect()
}