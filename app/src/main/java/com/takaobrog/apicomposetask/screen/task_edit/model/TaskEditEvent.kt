package com.takaobrog.apicomposetask.screen.task_edit.model

sealed class TaskEditEvent {
    object OnSubmit : TaskEditEvent()

    data class OnValueChangeTitle(val title: String) : TaskEditEvent()

    data class OnValueChangeComment(val comment: String) : TaskEditEvent()

    data class OnValueChangeTargetDate(val targetDate: Long?) : TaskEditEvent()

    object OnBackEvent : TaskEditEvent()
}