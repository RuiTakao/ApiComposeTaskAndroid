package com.takaobrog.apicomposetask.screen.task_list.model

import com.takaobrog.component.model.ErrorState
import com.takaobrog.core.domain.data.GetTaskListResponse

sealed class TaskListUiState {
    object Loading : TaskListUiState()
    data class Success(val list: List<GetTaskListResponse>) : TaskListUiState()
}