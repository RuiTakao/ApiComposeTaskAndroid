package com.takaobrog.apicomposetask.screen.task_create.model

sealed class TaskCreateEffect {
    data object NavigateBack : TaskCreateEffect()
}