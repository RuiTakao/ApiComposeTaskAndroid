package com.takaobrog.component.screen

import androidx.compose.runtime.Composable
import com.takaobrog.component.component.dialog.ErrorDialog
import com.takaobrog.component.component.dialog.OkCancelDialog
import com.takaobrog.component.model.DialogState

@Composable
fun DialogScreen(
    state: DialogState,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit = {},
) {
    when (state) {
        DialogState.Idle -> null
        is DialogState.Confirm -> OkCancelDialog(
            onConfirm = onConfirm,
            onDismiss = onDismiss,
            title = state.title,
        )

        is DialogState.Error -> ErrorDialog(
            state = state.error,
            onDismiss = onDismiss,
        )
    }
}