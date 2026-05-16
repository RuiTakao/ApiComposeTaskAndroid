package com.takaobrog.component.component.scrollable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ScrollableBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState),
        contentAlignment = Alignment.Center,
        content = { content() }
    )
}

@Preview(showBackground = true)
@Composable
fun ScrollableBox_Preview() {
    ScrollableBox { }
}