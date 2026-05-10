package com.takaobrog.apicomposetask.screen.task_create.model

sealed class TaskCreateEvent {
    data object OnSubmit : TaskCreateEvent()

    data class OnValueChangeTitle(val title: String) : TaskCreateEvent()

    data class OnValueChangeComment(val comment: String) : TaskCreateEvent()

    data class OnValueChangeTargetDate(val targetDate: Long?) : TaskCreateEvent()

    data object OnBackEvent : TaskCreateEvent()
}