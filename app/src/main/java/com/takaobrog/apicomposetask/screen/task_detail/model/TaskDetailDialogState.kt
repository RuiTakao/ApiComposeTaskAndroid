package com.takaobrog.apicomposetask.screen.task_detail.model

import com.takaobrog.component.model.ErrorState

sealed class TaskDetailDialogState {
    object Idle : TaskDetailDialogState()
    data class DeleteConfirm(val title: String) : TaskDetailDialogState()
    data class Error(val error: ErrorState) : TaskDetailDialogState()
}