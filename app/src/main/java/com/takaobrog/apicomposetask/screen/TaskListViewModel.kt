package com.takaobrog.apicomposetask.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.apicomposetask.screen.model.TaskListUiState
import com.takaobrog.apicomposetask.util.ErrorState
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

    private val _errorState = MutableStateFlow<ErrorState?>(null)
    val errorState = _errorState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        load()
    }

    fun onRefresh() {
        _isRefreshing.value = true
        load()
    }

    fun onDismiss() {
        Log.d("DEBUG", "uiState ${uiState.value}")
        // TODO 初期表示と表示済みで出し分け
        _isRefreshing.value = false

        when (errorState.value) {
            ErrorState.NetworkError -> {

            }

            is ErrorState.SystemError -> {

            }

            else -> {}
        }
        _errorState.value = null
    }

    private fun load() {
        viewModelScope.launch {
            repository.getTaskList()
                .catch { e ->
                    _isRefreshing.value = false

                    Log.d("DEBUG", "error $e")

                    // TODO 初期表示と表示済みで出し分け
                    if (e is ConnectException) {
                        _errorState.value = ErrorState.NetworkError
                    } else {
                        _errorState.value = ErrorState.SystemError(message = e.message)
                    }
                    _uiState.value = TaskListUiState.Success(list = emptyList())
                }.collect { list ->
                    _isRefreshing.value = false
                    _uiState.value = TaskListUiState.Success(list = list)
                }
        }
    }
}