package com.takaobrog.core.data.repository

import android.util.Log
import com.takaobrog.core.data.service.TaskApiService
import com.takaobrog.core.domain.data.CreateTaskRequest
import com.takaobrog.core.domain.data.GetTaskListResponse
import com.takaobrog.core.domain.repository.TaskRepository
import com.takaobrog.core.util.TimeProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val service: TaskApiService,
    private val timeProvider: TimeProvider,
) : TaskRepository {

    override fun getTaskList(): Flow<List<GetTaskListResponse>> = flow {
        val response = service.getTaskList()
        emit(response)
    }

    override fun getTask(id: Int): Flow<GetTaskListResponse> = flow {
        val response = service.getTask(id = id)
        emit(response)
    }

    override fun createTask(title: String): Flow<Result<Unit>> = flow {
        try {
            val createTaskRequest = CreateTaskRequest(
                title = title,
                createdAt = timeProvider.now(),
            )
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

    override fun updateTask(
        id: Int,
        createTaskRequest: CreateTaskRequest,
    ): Flow<Result<Unit>> = flow {
        try {
            val request = service.updateTask(id = id, createTaskRequest = createTaskRequest)

            if (request.isSuccessful) {
                emit(Result.success(Unit))
            } else {
                Log.d("DEBUG", "code ${request.code()}")
                emit(Result.failure(Exception("更新失敗")))
            }
        } catch (e: Exception) {
            Log.d("DEBUG", "code $e")
            emit(Result.failure(exception = e))
        }
    }

    override fun deleteTask(id: Int): Flow<Result<Unit>> = flow {
        try {
            val request = service.deleteTask(id = id)

            Log.d("DEBUG", "code ${request.code()}")
            if (request.isSuccessful) {
                emit(Result.success(Unit))
            } else {
                emit(Result.failure(Exception("削除失敗")))
            }
        } catch (e: Exception) {
            Log.d("DEBUG", "error $e")
            emit(Result.failure(exception = e))
        }
    }
}