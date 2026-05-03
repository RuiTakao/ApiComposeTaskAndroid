package com.takaobrog.apicomposetask.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.apicomposetask.screen.model.TaskListUiState
import com.takaobrog.core.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<TaskListUiState>(TaskListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _isShowDialog = MutableStateFlow(false)
    val isShowDialog = _isShowDialog.asStateFlow()

    init {
       load()
    }

    fun onRefresh() {
        _isRefreshing.value = true
        load()
    }

    fun onDismiss() {
        _isShowDialog.value = false
    }

    private fun load() {
        viewModelScope.launch {
            repository.getTaskList()
                .catch { e ->
                    _isShowDialog.value = true
                    _uiState.value = TaskListUiState.Error(message = e.message)
                }.collect { list ->
                    _isRefreshing.value = false
                    _uiState.value = TaskListUiState.Success(list = list)
                }
        }
    }
}