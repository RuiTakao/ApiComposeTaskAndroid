package com.takaobrog.apicomposetask.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.apicomposetask.screen.model.TaskListUiState
import com.takaobrog.component.model.ErrorState
import com.takaobrog.component.model.ReloadState
import com.takaobrog.core.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<TaskListUiState>(TaskListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _reloadState = MutableStateFlow<ReloadState>(ReloadState.Idle)
    val reloadState = _reloadState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getTaskList()
                .catch { e ->
                    if (e is ConnectException) {
                        _uiState.value = TaskListUiState.Error(error = ErrorState.NetworkError)
                    } else {
                        _uiState.value =
                            TaskListUiState.Error(error = ErrorState.SystemError(message = e.message))
                    }
                }.collect { list ->
                    _uiState.value = TaskListUiState.Success(list = list)
                }
        }
    }

    fun onRefresh() {
        _isRefreshing.value = true
        reload()
    }

    fun onDismiss() {
        reload()
    }

    private fun reload() {
        _reloadState.value = ReloadState.Reloading
        viewModelScope.launch {
            repository.getTaskList()
                .catch { e ->
                    _isRefreshing.value = false
                    if (e is ConnectException) {
                        _reloadState.value = ReloadState.Error(error = ErrorState.NetworkError)
                    } else {
                        _reloadState.value =
                            ReloadState.Error(error = ErrorState.SystemError(message = e.message))
                    }
                }.collect { list ->
                    _isRefreshing.value = false
                    _reloadState.value = ReloadState.Idle
                    _uiState.value = TaskListUiState.Success(list = list)
                }
        }
    }
}