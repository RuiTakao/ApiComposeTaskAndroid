package com.takaobrog.apicomposetask.screen.model

sealed class TaskListEvent {
    object OnRefresh : TaskListEvent()
    object OnDismiss : TaskListEvent()
}