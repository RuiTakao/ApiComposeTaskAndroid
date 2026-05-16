package com.takaobrog.apicomposetask.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.takaobrog.apicomposetask.screen.task_detail.model.TaskDetailDialogState
import com.takaobrog.component.screen.ErrorDialogScreen

@Composable
fun TaskDetailDialogScreen(
    state: TaskDetailDialogState,
    onDismiss: () -> Unit,
    onDelete: () -> Unit = {},
) {
    when (state) {
        TaskDetailDialogState.Idle -> null
        is TaskDetailDialogState.DeleteConfirm -> {
            AlertDialog(
                onDismissRequest = onDismiss,
                title = { Text(text = state.title) },
                confirmButton = {
                    TextButton(onClick = onDelete) {
                        Text(text = "OK")
                    }
                },
            )
        }

        is TaskDetailDialogState.Error -> ErrorDialogScreen(
            state = state.error,
            onDismiss = onDismiss,
        )
    }
}