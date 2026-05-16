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

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

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
        viewModelScope.launch {
            repository.deleteTask(id = 1)
                .collect { result ->
                result.fold(
                    onSuccess = {
                        Log.d("DEBUG", "success $it")
                    },
                    onFailure = { e ->
                        Log.d("DEBUG", "e $e")
                    }
                )
            }
        }
    }

    fun onRefresh() {
        _isRefreshing.value = true

        // TODO: リトライで正規実装
        viewModelScope.launch {
            repository.getTask(id = 1)
                .catch {
                    _isRefreshing.value = false
                    Log.e("DEBUG", "error $it")
                }
                .collect { item ->
                    _isRefreshing.value = false
                    _uiState.value = TaskDetailUiState.Success(item = item)
                }
        }
    }
}