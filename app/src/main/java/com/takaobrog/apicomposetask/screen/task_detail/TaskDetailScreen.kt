package com.takaobrog.apicomposetask.screen.task_detail

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
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
import com.takaobrog.apicomposetask.component.ProgressPercentItem
import com.takaobrog.apicomposetask.component.TargetDateText
import com.takaobrog.apicomposetask.screen.task_detail.model.TaskDetailEvent
import com.takaobrog.apicomposetask.screen.task_detail.model.TaskDetailUiState
import com.takaobrog.component.component.DefaultText
import com.takaobrog.component.component.app_bar.DefaultTopAppBarBack
import com.takaobrog.component.component.button.DoubleButton
import com.takaobrog.component.component.scrollable.ScrollableColumn
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
                is TaskDetailUiState.Success -> SuccessScreen(item = state.item, onEvent = onEvent)
                is TaskDetailUiState.Error -> {}
            }
        }
    }
}

@Composable
private fun SuccessScreen(
    item: GetTaskListResponse,
    onEvent: (TaskDetailEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    ScrollableColumn(modifier = modifier.padding(all = 16.dp)) {
        DefaultText(
            text = item.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        ProgressPercentItem(
            label = stringResource(id = R.string.task_detail_item_progress_percent_label),
            progressPercent = .3f,
            modifier = Modifier.padding(top = 16.dp),
        )
        TargetDateText("2026/5/14", modifier = Modifier.padding(top = 8.dp))
        DefaultText(
            text = "API　サーバ作成",
            modifier = Modifier.padding(top = 8.dp)
        )
        DoubleButton(
            leftButtonText = stringResource(id = R.string.task_detail_item_edit_button),
            rightButtonText = stringResource(id = R.string.task_detail_item_delete_button),
            onClickLeftButton = { onEvent(TaskDetailEvent.OnEditTaskEvent(id = 1)) },
            onClickRightButton = { onEvent(TaskDetailEvent.OnDeleteConfirmClick) },
            modifier = Modifier.padding(top = 16.dp),
        )
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
