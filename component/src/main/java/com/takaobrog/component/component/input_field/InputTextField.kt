package com.takaobrog.component.component.input_field

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InputTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    DefaultTextField(
        label = label,
        value = value,
        modifier = modifier,
        onValueChange = { onValueChange(it) },
    )
}

@Preview
@Composable
fun InputTextField_Preview_emptyValue() {
    InputTextField(
        label = "タスク名",
        value = "",
        onValueChange = {},
    )
}

@Preview
@Composable
fun InputTextField_Preview_notEmptyValue() {
    InputTextField(
        label = "タスク名",
        value = "Room学習",
        onValueChange = {},
    )
}