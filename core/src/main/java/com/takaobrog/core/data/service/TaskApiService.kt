package com.takaobrog.core.data.service

import com.takaobrog.core.domain.data.GetTaskListResponse
import retrofit2.http.GET

interface TaskApiService {

    @GET("tasks/get_task_list")
    suspend fun getTaskList(): List<GetTaskListResponse>
}