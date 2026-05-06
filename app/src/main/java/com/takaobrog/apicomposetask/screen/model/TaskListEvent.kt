package com.takaobrog.apicomposetask.screen.model

import com.takaobrog.apicomposetask.util.ErrorState

sealed class TaskListEvent {
    object OnRefresh : TaskListEvent()
    data class OnDismiss(val error: ErrorState) : TaskListEvent()
}