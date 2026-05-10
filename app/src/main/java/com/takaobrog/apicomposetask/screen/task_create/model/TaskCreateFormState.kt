package com.takaobrog.apicomposetask.screen.task_create.model

data class TaskCreateFormState(
    val title: String = "",
    val comment: String = "",
    val targetDate: Long? = null,
    val formatTargetDate: String = "",
)
