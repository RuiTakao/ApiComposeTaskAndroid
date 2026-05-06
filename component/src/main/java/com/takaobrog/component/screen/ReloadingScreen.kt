package com.takaobrog.component.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.component.R
import com.takaobrog.component.model.ErrorState
import com.takaobrog.component.model.ReloadState

@Composable
fun ReloadingScreen(
    state: ReloadState,
    onDismissError: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        ReloadState.Idle -> null
        ReloadState.Reloading -> LoadingScreen(
            modifier = modifier,
            color = colorResource(id = R.color.reloading_indicator_color),
            alpha = 0.6f,
        )

        is ReloadState.Error -> ErrorScreen(
            state = state.error,
            onDismiss = { onDismissError() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReloadingScreen_Preview_Idle() {
    ReloadingScreen(state = ReloadState.Idle, onDismissError = {})
}

@Preview(showBackground = true)
@Composable
fun ReloadingScreen_Preview_Reloading() {
    ReloadingScreen(state = ReloadState.Reloading, onDismissError = {})
}

@Preview(showBackground = true)
@Composable
fun ReloadingScreen_Preview_NetworkError() {
    ReloadingScreen(
        state = ReloadState.Error(error = ErrorState.NetworkError),
        onDismissError = {},
    )
}

@Preview(showBackground = true)
@Composable
fun ReloadingScreen_Preview_SystemError() {
    ReloadingScreen(
        state = ReloadState.Error(error = ErrorState.SystemError(message = "404")),
        onDismissError = {},
    )
}