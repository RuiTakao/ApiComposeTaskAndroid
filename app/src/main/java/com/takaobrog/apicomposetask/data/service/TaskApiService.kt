package com.takaobrog.apicomposetask.data.service

import com.takaobrog.apicomposetask.domain.data.GetTaskListResponse
import retrofit2.http.GET

interface TaskApiService {

    @GET("tasks/get_task_list")
    suspend fun getTaskList(): List<GetTaskListResponse>
}