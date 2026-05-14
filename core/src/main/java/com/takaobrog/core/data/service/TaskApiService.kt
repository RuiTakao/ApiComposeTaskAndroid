package com.takaobrog.core.data.service

import com.takaobrog.core.domain.data.CreateTaskRequest
import com.takaobrog.core.domain.data.GetTaskListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskApiService {

    @GET("tasks/get_task_list")
    suspend fun getTaskList(): List<GetTaskListResponse>

    @GET("tasks/get_task/{id}")
    suspend fun getTask(
        @Path("id") id: Int,
    ): GetTaskListResponse

    @POST("tasks/create_task")
    suspend fun createTask(
        @Body createTaskRequest: CreateTaskRequest,
    ): Response<Unit>

    @DELETE("tasks/delete_task/{id}")
    suspend fun deleteTask(
        @Path("id") id: Int,
    ): Response<Unit>
}