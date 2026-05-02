package com.takaobrog.apicomposetask.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.apicomposetask.screen.model.TaskListUiState
import com.takaobrog.core.domain.data.GetTaskListResponse

@Composable
fun TaskListScreen(
    state: TaskListUiState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
    ) { paddingValues ->
        Column(modifier.padding(paddingValues = paddingValues)) {

            when (state) {
                TaskListUiState.Loading -> {
                    Log.d("DEBUG", "loading")
                    Text(text = "loading")
                }

                is TaskListUiState.Success -> {
                    Log.d("DEBUG", "state ${state.list}")
                    Text(text = "state ${state.list}")
                }

                is TaskListUiState.Error -> {
                    Log.d("DEBUG", "error ${state.message}")
                    Text(text = "error ${state.message}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListScreen_Preview_Loading() {
    val state = TaskListUiState.Loading
    TaskListScreen(state = state)
}

@Preview(showBackground = true)
@Composable
fun TaskListScreen_Preview_Success() {
    val list = listOf(
        GetTaskListResponse(id = 1, title = "test1"),
        GetTaskListResponse(id = 2, title = "test2"),
    )
    val state = TaskListUiState.Success(list = list)
    TaskListScreen(state = state)
}

@Preview(showBackground = true)
@Composable
fun TaskListScreen_Preview_Error() {
    val state = TaskListUiState.Error(message = "404")
    TaskListScreen(state = state)
}