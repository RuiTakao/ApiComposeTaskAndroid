package com.takaobrog.core.data.repository

import android.util.Log
import com.takaobrog.core.data.service.TaskApiService
import com.takaobrog.core.domain.data.CreateTaskRequest
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

    override fun createTask(createTaskRequest: CreateTaskRequest): Flow<Result<Unit>> = flow {
        try {
            val request = service.createTask(createTaskRequest = createTaskRequest)

            if (request.isSuccessful) {
                emit(Result.success(Unit))
            } else {
                Log.d("DEBUG", "code ${request.code()}")
                emit(Result.failure(Exception("登録失敗")))
            }
        } catch (e: Exception) {
            Log.d("DEBUG", "code $e")
            emit(Result.failure(exception = e))
        }
    }
}