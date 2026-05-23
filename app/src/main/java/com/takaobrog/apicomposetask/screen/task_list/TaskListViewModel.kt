package com.takaobrog.apicomposetask.screen.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.apicomposetask.screen.task_list.model.TaskListUiState
import com.takaobrog.component.model.ConverterState
import com.takaobrog.component.model.DialogState
import com.takaobrog.component.model.FrontLayerState
import com.takaobrog.core.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val converterState: ConverterState,
) : ViewModel() {
    private val _uiState = MutableStateFlow<TaskListUiState>(TaskListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _frontLayerState = MutableStateFlow<FrontLayerState>(FrontLayerState.Idle)
    val frontLayerState = _frontLayerState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getTaskList()
                .catch { e ->
                    _frontLayerState.value = FrontLayerState.Error(error = converterState.errorState(e = e))
                }.collect { list ->
                    _uiState.value = TaskListUiState.Success(list = list)
                }
        }
    }

    fun onRefresh() {
        _isRefreshing.value = true
        viewModelScope.launch {
            repository.getTaskList()
                .catch { e ->
                    _frontLayerState.value = FrontLayerState.Error(error = converterState.errorState(e = e))
                }
                .onCompletion {
                    _isRefreshing.value = false
                }
                .collect { list ->
                    _uiState.value = TaskListUiState.Success(list = list)
                }
        }
    }

    fun onDismiss() {
        _frontLayerState.value = FrontLayerState.Idle
    }
}