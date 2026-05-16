package com.takaobrog.apicomposetask.screen.task_detail.model

sealed class TaskDetailEvent {
    object OnRefresh : TaskDetailEvent()
    object OnDeleteConfirmClick : TaskDetailEvent()
    data class OnEditTaskEvent(val id: Int) : TaskDetailEvent()
    object OnBackEvent : TaskDetailEvent()
}