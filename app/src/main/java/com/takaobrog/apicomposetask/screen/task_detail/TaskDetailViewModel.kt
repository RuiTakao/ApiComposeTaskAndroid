package com.takaobrog.apicomposetask.screen.task_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.apicomposetask.screen.task_detail.model.TaskDetailUiState
import com.takaobrog.core.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val repository: TaskRepository,
): ViewModel() {
    private val _uiState = MutableStateFlow<TaskDetailUiState>(TaskDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getTask(id = 1)
                .catch { Log.e("DEBUG", "error $it") }
                .collect { item ->
                    _uiState.value = TaskDetailUiState.Success(item = item)
                }
        }
    }

    fun deleteConfirm() {
        Log.d("DEBUG", "deleteConfirm")
    }
}