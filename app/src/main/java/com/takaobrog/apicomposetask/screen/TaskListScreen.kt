package com.takaobrog.apicomposetask.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.takaobrog.apicomposetask.R
import com.takaobrog.apicomposetask.component.DefaultText
import com.takaobrog.apicomposetask.component.ErrorScreen
import com.takaobrog.apicomposetask.component.LoadingScreen
import com.takaobrog.apicomposetask.component.ScrollableBox
import com.takaobrog.apicomposetask.component.TaskListItem
import com.takaobrog.apicomposetask.screen.model.TaskListEvent
import com.takaobrog.apicomposetask.screen.model.TaskListUiState
import com.takaobrog.apicomposetask.util.ErrorState
import com.takaobrog.core.domain.data.GetTaskListResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    state: TaskListUiState,
    onEvent: (TaskListEvent) -> Unit,
    isRefreshing: Boolean,
) {
    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = { onEvent(TaskListEvent.OnRefresh) },
            modifier = Modifier.padding(paddingValues = paddingValues),
        ) {
            when (state) {
                TaskListUiState.Loading -> LoadingScreen()

                is TaskListUiState.Success -> if (state.list.isEmpty()) SuccessEmptyScreen() else SuccessScreen(
                    list = state.list
                )

                is TaskListUiState.Error -> ErrorScreen(
                    state = state.error,
                    onDismiss = { onEvent(TaskListEvent.OnDismiss(error = state.error)) }
                )
            }
        }
    }
}

@Composable
private fun SuccessScreen(list: List<GetTaskListResponse>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
    ) {
        items(list) { item ->
            TaskListItem(
                title = item.title,
                progressPercent = 0.3f,
                targetDate = null,
                isTargetDateOver = false,
                onItemClick = {},
            )
        }
    }
}

@Composable
private fun SuccessEmptyScreen() {
    ScrollableBox {
        DefaultText(
            text = stringResource(id = R.string.task_list_empty),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListScreen_Preview_Loading() {
    val state = TaskListUiState.Loading
    TaskListScreen(state = state, onEvent = {}, isRefreshing = false)
}

@Preview(showBackground = true)
@Composable
fun TaskListScreen_Preview_Success() {
    val list = listOf(
        GetTaskListResponse(id = 1, title = "test1"),
        GetTaskListResponse(id = 2, title = "test2"),
    )
    val state = TaskListUiState.Success(list = list)
    TaskListScreen(state = state, onEvent = {}, isRefreshing = false)
}

@Preview(showBackground = true)
@Composable
fun TaskListScreen_Preview_SuccessEmpty() {
    val state = TaskListUiState.Success(list = listOf())
    TaskListScreen(state = state, onEvent = {}, isRefreshing = false)
}

@Preview(showBackground = true)
@Composable
fun TaskListScreen_Preview_NetworkError() {
    val state = TaskListUiState.Error(error = ErrorState.NetworkError)
    TaskListScreen(state = state, onEvent = {}, isRefreshing = false)
}

@Preview(showBackground = true)
@Composable
fun TaskListScreen_Preview_SystemError() {
    val state = TaskListUiState.Error(error = ErrorState.SystemError(message = "404"))
    TaskListScreen(state = state, onEvent = {}, isRefreshing = false)
}