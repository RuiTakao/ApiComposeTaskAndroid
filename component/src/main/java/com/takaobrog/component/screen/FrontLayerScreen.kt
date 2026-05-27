package com.takaobrog.component.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.component.R
import com.takaobrog.component.component.dialog.ErrorDialog
import com.takaobrog.component.component.dialog.OkCancelDialog
import com.takaobrog.component.model.ErrorState
import com.takaobrog.component.model.FrontLayerState

@Composable
fun FrontLayerScreen(
    state: FrontLayerState,
    onDismissErrorDialog: () -> Unit,
    onConfirm: () -> Unit = {},
    onDismissConfirmDialog: () -> Unit = {},
) {
    when (state) {
        FrontLayerState.Idle -> null

        FrontLayerState.Loading -> LoadingScreen(
            color = colorResource(id = R.color.reloading_indicator_color),
            alpha = 0.6f,
        )

        is FrontLayerState.Confirm -> OkCancelDialog(
            onConfirm = { onConfirm() },
            onDismiss = { onDismissConfirmDialog() },
            title = state.title,
        )

        is FrontLayerState.Error -> ErrorDialog(
            state = state.error,
            onDismiss = { onDismissErrorDialog() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FrontLayerScreen_Idle_Preview() {
    FrontLayerScreen(state = FrontLayerState.Idle, onDismissErrorDialog = {})
}

@Preview(showBackground = true)
@Composable
fun FrontLayerScreen_Loading_Preview() {
    FrontLayerScreen(state = FrontLayerState.Loading, onDismissErrorDialog = {})
}

@Preview(showBackground = true)
@Composable
fun FrontLayerScreen_Confirm_Preview() {
    FrontLayerScreen(state = FrontLayerState.Confirm(title = "確認"), onDismissErrorDialog = {})
}

@Preview(showBackground = true)
@Composable
fun FrontLayerScreen_Error_Preview() {
    FrontLayerScreen(
        state = FrontLayerState.Error(error = ErrorState.NetworkError),
        onDismissErrorDialog = {},
    )
}