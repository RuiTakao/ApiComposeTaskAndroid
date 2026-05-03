package com.takaobrog.apicomposetask.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.apicomposetask.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OkDialog(
    onDismiss: () -> Unit,
    title: String,
    titleColor: Color = colorResource(id = R.color.base_color),
    confirmText: String = stringResource(id = R.string.dialog_confirm),
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { DefaultText(text = title, color = titleColor) },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                DefaultText(text = confirmText)
            }
        },
    )
}

@Preview
@Composable
fun ErrorDialog_Preview() {
    OkDialog(onDismiss = {}, title = "タイトルを入力してください")
}