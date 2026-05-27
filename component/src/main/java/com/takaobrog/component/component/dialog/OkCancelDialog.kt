package com.takaobrog.component.component.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.component.R

@Composable
internal fun OkCancelDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    title: String,
    titleColor: Color = colorResource(id = R.color.base_color),
    confirmText: String = stringResource(id = R.string.dialog_confirm),
    dismissText: String = stringResource(id = R.string.dialog_cansel)
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title, color = titleColor) },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text(text = confirmText) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(text = dismissText) }
        }
    )
}

@Preview
@Composable
fun OkCancelDialog_Preview() {
    OkCancelDialog(onConfirm = {}, onDismiss = {}, title = "削除しますか？")
}