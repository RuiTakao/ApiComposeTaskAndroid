package com.takaobrog.component.component.input_field

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInputField(
    label: String,
    value: String?,
    onValueChange: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isDialog by rememberSaveable { mutableStateOf(false) }
    val dateState = rememberDatePickerState()
    val focusManager = LocalFocusManager.current

    DefaultTextField(
        label = label,
        value = value ?: "",
        modifier = modifier.onFocusChanged { focusState ->
            if (focusState.isFocused) {
                isDialog = true
                focusManager.clearFocus()
            }
        },
        readOnly = true,
    )

    if (isDialog) {
        DatePickerDialog(
            onDismissRequest = { isDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        dateState.selectedDateMillis?.let { onValueChange(it) }
                        isDialog = false
                    }
                ) {
                    Text("OK")
                }
            }
        ) {
            DatePicker(state = dateState)
        }
    }
}

@Preview
@Composable
fun DateInputField_Preview_emptyValue() {
    DateInputField(
        label = "期限",
        value = "",
        onValueChange = {},
    )
}

@Preview
@Composable
fun DateInputField_Preview_notEmptyValue() {
    DateInputField(
        label = "期限",
        value = "2026/4/23",
        onValueChange = {},
    )
}