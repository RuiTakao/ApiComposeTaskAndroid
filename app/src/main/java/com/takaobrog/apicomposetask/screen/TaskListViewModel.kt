package com.takaobrog.apicomposetask.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.core.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository,
) : ViewModel() {
    fun test() {
        viewModelScope.launch {
            repository.getTaskList().collect {
                Log.d("DEBUG", "getTaskList $it")
            }
        }
    }
}