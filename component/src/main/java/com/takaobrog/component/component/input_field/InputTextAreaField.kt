package com.takaobrog.component.component.input_field

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InputTextAreaField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    DefaultTextField(
        label = label,
        value = value,
        modifier = modifier
            .height(height = 200.dp),
        onValueChange = { onValueChange(it) },
    )
}

@Preview
@Composable
fun InputTextAreaField_Preview_emptyValue() {
    InputTextAreaField(
        label = "タスク名",
        value = "",
        onValueChange = {},
    )
}

@Preview
@Composable
fun InputTextAreaField_Preview_notEmptyValue() {
    InputTextAreaField(
        label = "タスク名",
        value = "Room学習",
        onValueChange = {},
    )
}