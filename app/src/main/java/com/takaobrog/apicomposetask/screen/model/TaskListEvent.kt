package com.takaobrog.apicomposetask.screen.model

import com.takaobrog.component.model.ErrorState

sealed class TaskListEvent {
    object OnRefresh : TaskListEvent()
    data class OnDismiss(val error: ErrorState) : TaskListEvent()
}