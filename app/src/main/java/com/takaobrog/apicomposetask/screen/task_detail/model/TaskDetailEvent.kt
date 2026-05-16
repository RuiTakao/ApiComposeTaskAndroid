package com.takaobrog.apicomposetask.screen.task_detail.model

import com.takaobrog.component.model.ErrorState

sealed class TaskDetailEvent {
    object OnRefresh : TaskDetailEvent()
    data class OnDismiss(val error: ErrorState) : TaskDetailEvent()
    object OnDeleteConfirmClick : TaskDetailEvent()
    data class OnEditTaskEvent(val id: Int) : TaskDetailEvent()
    object OnBackEvent : TaskDetailEvent()
}