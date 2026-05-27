package com.takaobrog.component.component.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.component.R
import com.takaobrog.component.model.ErrorState

@Composable
internal fun ErrorDialog(state: ErrorState, onDismiss: () -> Unit) {
    when (state) {
        ErrorState.NetworkError -> {
            OkDialog(
                onDismiss = onDismiss,
                title = "ネットワークに接続されていません",
                titleColor = colorResource(id = R.color.danger_color),
            )
        }

        is ErrorState.SystemError -> {
            OkDialog(
                onDismiss = onDismiss,
                title = state.message ?: "",
                titleColor = colorResource(id = R.color.danger_color),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NetworkErrorScreen_Preview() {
    ErrorDialog(state = ErrorState.NetworkError, onDismiss = {})
}

@Preview(showBackground = true)
@Composable
fun SystemErrorScreen_Preview() {
    ErrorDialog(state = ErrorState.SystemError(message = "404"), onDismiss = {})
}