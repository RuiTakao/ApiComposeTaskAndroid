package com.takaobrog.core.domain.repository

import com.takaobrog.core.domain.data.CreateTaskRequest
import com.takaobrog.core.domain.data.GetTaskListResponse
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTaskList(): Flow<List<GetTaskListResponse>>

    fun getTask(id: Int): Flow<GetTaskListResponse>

    fun createTask(title: String): Flow<Result<Unit>>

    fun updateTask(id: Int, createTaskRequest: CreateTaskRequest): Flow<Result<Unit>>

    fun deleteTask(id: Int): Flow<Result<Unit>>
}