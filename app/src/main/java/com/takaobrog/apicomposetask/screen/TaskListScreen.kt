package com.takaobrog.apicomposetask.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.takaobrog.apicomposetask.R
import com.takaobrog.apicomposetask.component.DefaultText
import com.takaobrog.apicomposetask.component.OkDialog
import com.takaobrog.apicomposetask.component.TaskListItem
import com.takaobrog.apicomposetask.screen.model.TaskListUiState
import com.takaobrog.core.domain.data.GetTaskListResponse

@Composable
fun TaskListScreen(state: TaskListUiState) {
    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues = paddingValues)) {

            when (state) {
                TaskListUiState.Loading -> ScreenLoading()

                is TaskListUiState.Success ->
                    if (state.list.isEmpty()) ScreenSuccessEmpty() else ScreenSuccess(list = state.list)

                is TaskListUiState.Error -> ScreenError(message = state.message)
            }
        }
    }
}

@Composable
private fun ScreenLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ScreenSuccess(list: List<GetTaskListResponse>) {
    LazyColumn(
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
private fun ScreenSuccessEmpty() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        DefaultText(
            text = stringResource(id = R.string.task_list_empty),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun ScreenError(message: String?) {
    OkDialog(
        onDismiss = {},
        title = message ?: "",
        titleColor = colorResource(id = R.color.danger_color)
    )
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
fun TaskListScreen_Preview_SuccessEmpty() {
    val state = TaskListUiState.Success(list = listOf())
    TaskListScreen(state = state)
}

@Preview(showBackground = true)
@Composable
fun TaskListScreen_Preview_Error() {
    val state = TaskListUiState.Error(message = "404")
    TaskListScreen(state = state)
}