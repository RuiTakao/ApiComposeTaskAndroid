package com.takaobrog.apicomposetask.screen.task_detail.model

sealed class TaskDetailEvent {
    data object OnDeleteConfirmClick : TaskDetailEvent()
    data class OnEditTaskEvent(val id: Int) : TaskDetailEvent()
    data object OnBackEvent : TaskDetailEvent()
}