package com.takaobrog.core.data.repository

import com.takaobrog.core.data.service.TaskApiService
import com.takaobrog.core.domain.data.GetTaskListResponse
import com.takaobrog.core.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val service: TaskApiService,
) : TaskRepository {

    override fun getTaskList(): Flow<List<GetTaskListResponse>> = flow {
        val response = service.getTaskList()
        emit(response)
    }
}