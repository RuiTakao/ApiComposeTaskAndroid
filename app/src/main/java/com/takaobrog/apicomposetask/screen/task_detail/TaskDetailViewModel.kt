package com.takaobrog.apicomposetask.screen.task_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.apicomposetask.screen.task_detail.model.TaskDetailEffect
import com.takaobrog.apicomposetask.screen.task_detail.model.TaskDetailUiState
import com.takaobrog.component.model.ConverterState
import com.takaobrog.component.model.DialogState
import com.takaobrog.component.model.FrontLayerState
import com.takaobrog.core.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val converterState: ConverterState,
) : ViewModel() {
    private val _uiState = MutableStateFlow<TaskDetailUiState>(TaskDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<TaskDetailEffect>()
    val effect = _effect.asSharedFlow()

    private val _frontLayerState = MutableStateFlow<FrontLayerState>(FrontLayerState.Idle)
    val frontLayerState = _frontLayerState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getTask(id = 1)
                .catch { e ->
                    Log.e("DEBUG", "error $e")
                    _frontLayerState.value =
                        FrontLayerState.Error(error = converterState.errorState(e = e))
                }
                .collect { item ->
                    _uiState.value = TaskDetailUiState.Success(item = item)
                }
        }
    }

    fun deleteConfirm(title : String) {
        _frontLayerState.value = FrontLayerState.Confirm(title = "${title}を削除しますか？")
    }

    fun onDelete() {
        _frontLayerState.value = FrontLayerState.Idle
        viewModelScope.launch {
            repository.deleteTask(id = 1)
                .collect { result ->
                    result.fold(
                        onSuccess = {
                            Log.d("DEBUG", "success $it")
                            _effect.emit(TaskDetailEffect.OnBackEvent)
                        },
                        onFailure = { e ->
                            Log.d("DEBUG", "e $e")
                            _frontLayerState.value =
                                FrontLayerState.Error(error = converterState.errorState(e = e))
                        }
                    )
                }
        }
    }

    fun onRefresh() {
        _isRefreshing.value = true

        viewModelScope.launch {
            repository.getTask(id = 1)
                .catch { e ->
                    _frontLayerState.value =
                        FrontLayerState.Error(error = converterState.errorState(e = e))
                    Log.e("DEBUG", "error $e")
                }
                .onCompletion {
                    _isRefreshing.value = false
                }
                .collect { item ->
                    _uiState.value = TaskDetailUiState.Success(item = item)
                }
        }
    }

    fun onDismiss() {
        val initialError =
            uiState.value is TaskDetailUiState.Loading && frontLayerState.value is FrontLayerState.Error

        if (initialError) {
            viewModelScope.launch {
                _effect.emit(TaskDetailEffect.OnBackEvent)
            }
        }
        _frontLayerState.value = FrontLayerState.Idle
    }
}