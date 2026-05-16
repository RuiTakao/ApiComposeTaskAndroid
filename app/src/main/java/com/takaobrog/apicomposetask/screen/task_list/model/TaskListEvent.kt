package com.takaobrog.apicomposetask.screen.task_list.model

sealed class TaskListEvent {
    object OnRefresh : TaskListEvent()
    object OnClickFab : TaskListEvent()
    data class OnClickItem(val id: Int) : TaskListEvent()
}