package com.takaobrog.apicomposetask.screen.task_edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.takaobrog.apicomposetask.R
import com.takaobrog.apicomposetask.screen.task_edit.model.TaskEditEvent
import com.takaobrog.apicomposetask.screen.task_edit.model.TaskEditFormState
import com.takaobrog.component.component.app_bar.DefaultTopAppBarBack
import com.takaobrog.component.component.button.DefaultButton
import com.takaobrog.component.component.input_field.DateInputField
import com.takaobrog.component.component.input_field.InputTextAreaField
import com.takaobrog.component.component.input_field.InputTextField

@Composable
fun TaskEditScreen(
    formState: TaskEditFormState,
    onEvent: (TaskEditEvent) -> Unit
) {
    Scaffold(
        topBar = {
            DefaultTopAppBarBack(onClick = { onEvent(TaskEditEvent.OnBackEvent) })
        },
        contentWindowInsets = WindowInsets.systemBars,
    ) { paddingValues ->
        Form(
            formState = formState,
            onEvent = onEvent,
            modifier = Modifier.padding(paddingValues = paddingValues),
        )
    }
}

@Composable
private fun Form(
    formState: TaskEditFormState,
    onEvent: (TaskEditEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        InputTextField(
            label = stringResource(id = R.string.task_create_form_title),
            value = formState.title,
            onValueChange = { onEvent(TaskEditEvent.OnValueChangeTitle(title = it)) },
        )

        DateInputField(
            label = stringResource(id = R.string.task_create_form_target_date),
            value = formState.formatTargetDate,
            onValueChange = { onEvent(TaskEditEvent.OnValueChangeTargetDate(targetDate = it)) },
        )

        InputTextAreaField(
            label = stringResource(id = R.string.task_create_form_comment),
            value = formState.comment,
            onValueChange = { onEvent(TaskEditEvent.OnValueChangeComment(comment = it)) },
        )

        DefaultButton(
            text = stringResource(id = R.string.task_create_form_submit),
            onClick = { onEvent(TaskEditEvent.OnSubmit) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp),
        )
    }
}