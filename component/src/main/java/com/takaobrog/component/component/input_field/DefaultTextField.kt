package com.takaobrog.component.component.input_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DefaultTextField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        readOnly = readOnly,
        label = { Text(text = label) },
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.Black,
            disabledIndicatorColor = Color.Black,
        )
    )
}

@Preview
@Composable
fun DefaultTextField_Preview_emptyValue() {
    DefaultTextField(
        label = "タイトル",
        value = "",
    )
}

@Preview
@Composable
fun DefaultTextField_Preview_notEmptyValue() {
    DefaultTextField(
        label = "タイトル",
        value = "Room学習",
    )
}