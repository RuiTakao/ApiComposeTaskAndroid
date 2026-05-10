package com.takaobrog.apicomposetask.screen.task_create

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.apicomposetask.screen.task_create.model.TaskCreateEffect
import com.takaobrog.apicomposetask.screen.task_create.model.TaskCreateFormState
import com.takaobrog.core.domain.data.CreateTaskRequest
import com.takaobrog.core.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskCreateViewModel @Inject constructor(
    private val repository: TaskRepository,
) : ViewModel() {
    private val _formState = MutableStateFlow(TaskCreateFormState())
    val formState = _formState.asStateFlow()

    private val _effect = MutableSharedFlow<TaskCreateEffect>()
    val effect = _effect.asSharedFlow()

    fun inputTitle(title: String) {
        _formState.update { state ->
            state.copy(title = title)
        }
    }

    fun inputComment(comment: String) {
        _formState.update { state ->
            state.copy(comment = comment)
        }
    }

    fun inputTargetDate(targetDate: Long?) {
        _formState.update { state ->
            state.copy(
                targetDate = targetDate,
                formatTargetDate = "2026/5/10",
            )
        }
    }

    fun onSubmit() {
        viewModelScope.launch {
            val response = repository.createTask(
                createTaskRequest = CreateTaskRequest(
                    title = _formState.value.title
                )
            )
            response.collect { result ->
                result.fold(
                    onSuccess = {
                        _effect.emit(TaskCreateEffect.NavigateBack)
                    },
                    onFailure = { e ->
                        Log.d("DEBUG", "e $e")
                    }
                )
            }
        }
    }
}