package com.takaobrog.apicomposetask.screen.model

import com.takaobrog.core.domain.data.GetTaskListResponse

sealed class TaskListUiState {
    object Loading : TaskListUiState()
    data class Success(val list: List<GetTaskListResponse>) : TaskListUiState()
    data class Error(val message: String?) : TaskListUiState()
}