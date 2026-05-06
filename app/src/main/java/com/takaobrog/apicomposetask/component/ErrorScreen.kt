package com.takaobrog.apicomposetask.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.apicomposetask.R
import com.takaobrog.apicomposetask.util.ErrorState

@Composable
fun ErrorScreen(state: ErrorState, onDismiss: () -> Unit) {
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
    ErrorScreen(state = ErrorState.NetworkError, onDismiss = {})
}

@Preview(showBackground = true)
@Composable
fun SystemErrorScreen_Preview() {
    ErrorScreen(state = ErrorState.SystemError(message = "404"), onDismiss = {})
}