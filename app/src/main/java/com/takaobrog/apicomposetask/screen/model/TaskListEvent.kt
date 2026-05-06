package com.takaobrog.apicomposetask.screen.model

import com.takaobrog.component.model.ErrorState

sealed class TaskListEvent {
    object OnRefresh : TaskListEvent()
    data class OnDismiss(val error: ErrorState) : TaskListEvent()
    object OnClickFab : TaskListEvent()
    data class OnClickItem(val id: Int) : TaskListEvent()
}