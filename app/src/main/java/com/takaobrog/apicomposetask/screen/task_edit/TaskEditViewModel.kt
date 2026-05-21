package com.takaobrog.apicomposetask.screen.task_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.apicomposetask.screen.task_edit.model.TaskEditEffect
import com.takaobrog.apicomposetask.screen.task_edit.model.TaskEditFormState
import com.takaobrog.core.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskEditViewModel @Inject constructor(
    private val repository: TaskRepository,
) : ViewModel() {
    private val _formState = MutableStateFlow<TaskEditFormState>(TaskEditFormState())
    val formState = _formState.asStateFlow()

    private val _effect = MutableSharedFlow<TaskEditEffect>()
    val effect = _effect.asSharedFlow()

    init {
        viewModelScope.launch {
            repository.getTask(id = 1)
                .catch { }
                .collect { item ->
                    _formState.update { state ->
                        state.copy(title = item.title)
                    }
                }
        }
    }

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
            _effect.emit(TaskEditEffect.NavigateBack)
        }
    }
}