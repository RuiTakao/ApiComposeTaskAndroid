package com.takaobrog.apicomposetask.domain.repository

import com.takaobrog.apicomposetask.domain.data.GetTaskListResponse
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTaskList() : Flow<List<GetTaskListResponse>>
}