package com.takaobrog.component.component.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.component.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OkDialog(
    onDismiss: () -> Unit,
    title: String,
    titleColor: Color = colorResource(id = R.color.base_color),
    confirmText: String = stringResource(id = R.string.dialog_confirm),
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title, color = titleColor) },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text(text = confirmText) }
        },
    )
}

@Preview
@Composable
fun OkDialog_Preview() {
    OkDialog(onDismiss = {}, title = "タイトルを入力してください")
}