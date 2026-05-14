package com.takaobrog.component.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DoubleButton(
    leftButtonText: String,
    rightButtonText: String,
    onClickLeftButton: () -> Unit,
    onClickRightButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        DefaultButton(text = leftButtonText, onClick = onClickLeftButton)
        DefaultButton(text = rightButtonText, onClick = onClickRightButton)
    }
}

@Preview(showBackground = true)
@Composable
fun DoubleButton_Preview() {
    DoubleButton(
        leftButtonText = "編集",
        rightButtonText = "削除",
        onClickLeftButton = {},
        onClickRightButton = {},
    )
}