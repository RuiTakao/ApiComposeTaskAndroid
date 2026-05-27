package com.takaobrog.apicomposetask.screen.task_detail.model

sealed class TaskDetailEffect {
    data object OnBackEvent : TaskDetailEffect()
}