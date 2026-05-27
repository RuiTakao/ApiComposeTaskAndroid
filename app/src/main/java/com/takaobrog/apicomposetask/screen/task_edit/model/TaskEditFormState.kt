package com.takaobrog.apicomposetask.screen.task_edit.model

data class TaskEditFormState(
    val title: String = "",
    val comment: String = "",
    val targetDate: Long? = null,
    val formatTargetDate: String = "",
)