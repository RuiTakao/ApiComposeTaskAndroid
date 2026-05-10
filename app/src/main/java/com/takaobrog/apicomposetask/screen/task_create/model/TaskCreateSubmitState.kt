package com.takaobrog.apicomposetask.screen.task_create.model

import com.takaobrog.component.model.ErrorState

sealed class TaskCreateSubmitState {
    object Sending : TaskCreateSubmitState()
    object Success : TaskCreateSubmitState()
    data class Error(val error: ErrorState) : TaskCreateSubmitState()
}