package com.takaobrog.apicomposetask.screen.model

sealed class TaskListErrorState {
    data class SystemError(val message: String?) : TaskListErrorState()
    object NetworkError : TaskListErrorState()
}