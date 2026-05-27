package com.takaobrog.apicomposetask.screen.task_detail.model

import com.takaobrog.core.domain.data.GetTaskListResponse

sealed class TaskDetailUiState {
    object Loading : TaskDetailUiState()
    data class Success(val item: GetTaskListResponse) : TaskDetailUiState()
}