package com.takaobrog.apicomposetask.screen.task_detail

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.apicomposetask.screen.task_detail.component.TaskDetailItem
import com.takaobrog.apicomposetask.screen.task_detail.model.TaskDetailEvent
import com.takaobrog.apicomposetask.screen.task_detail.model.TaskDetailUiState
import com.takaobrog.component.component.app_bar.DefaultTopAppBarBack
import com.takaobrog.component.screen.LoadingScreen
import com.takaobrog.core.domain.data.GetTaskListResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    state: TaskDetailUiState,
    onEvent: (TaskDetailEvent) -> Unit,
    isRefreshing: Boolean,
) {
    Scaffold(
        topBar = {
            DefaultTopAppBarBack(onClick = { onEvent(TaskDetailEvent.OnBackEvent) })
        },
        contentWindowInsets = WindowInsets.systemBars,
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = { onEvent(TaskDetailEvent.OnRefresh) },
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            when (state) {
                TaskDetailUiState.Loading -> LoadingScreen()

                is TaskDetailUiState.Success -> TaskDetailItem(
                    title = state.item.title,
                    comment = "API　サーバ作成",
                    progressPercent = .3f,
                    targetDate = "2026/5/14",
                    onClickEditButton = { onEvent(TaskDetailEvent.OnEditTaskEvent(id = state.item.id)) },
                    onClickDeleteButton = { onEvent(TaskDetailEvent.OnDeleteConfirmClick(title = state.item.title)) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailScreen_Preview_Loading() {
    val state = TaskDetailUiState.Loading
    TaskDetailScreen(state = state, onEvent = {}, isRefreshing = false)
}

@Preview(showBackground = true)
@Composable
fun TaskDetailScreen_Preview_Success() {
    val state = TaskDetailUiState.Success(
        item = GetTaskListResponse(
            id = 1,
            title = "Api学習",
        )
    )
    TaskDetailScreen(state = state, onEvent = {}, isRefreshing = false)
}
