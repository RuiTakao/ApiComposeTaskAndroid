package com.takaobrog.apicomposetask.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.apicomposetask.screen.model.TaskListUiState
import com.takaobrog.component.model.ConverterState
import com.takaobrog.component.model.ReloadState
import com.takaobrog.core.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val converterState: ConverterState,
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
                    _uiState.value = TaskListUiState.Error(error = converterState.errorState(e = e))
                }.collect { list ->
                    _uiState.value = TaskListUiState.Success(list = list)
                }
        }
    }

    fun onRefresh() {
        _isRefreshing.value = true
        _reloadState.value = ReloadState.Reloading
        viewModelScope.launch {
            reload()
        }
    }

    fun onDismiss() {
        _reloadState.value = ReloadState.Reloading
        viewModelScope.launch {
            delay(500)
            reload()
        }
    }

    private suspend fun reload() {
        repository.getTaskList()
            .catch { e ->
                _isRefreshing.value = false
                _reloadState.value = ReloadState.Error(error = converterState.errorState(e = e))
            }.collect { list ->
                _isRefreshing.value = false
                _reloadState.value = ReloadState.Idle
                _uiState.value = TaskListUiState.Success(list = list)
            }
    }
}