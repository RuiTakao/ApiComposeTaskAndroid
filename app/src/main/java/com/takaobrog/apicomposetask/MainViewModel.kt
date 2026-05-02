package com.takaobrog.apicomposetask

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.takaobrog.core.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
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