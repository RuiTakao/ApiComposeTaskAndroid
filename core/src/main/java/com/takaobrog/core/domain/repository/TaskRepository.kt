package com.takaobrog.core.domain.repository

import com.takaobrog.core.domain.data.CreateTaskRequest
import com.takaobrog.core.domain.data.GetTaskListResponse
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTaskList() : Flow<List<GetTaskListResponse>>

    fun createTask(createTaskRequest: CreateTaskRequest) : Flow<Result<Unit>>
}