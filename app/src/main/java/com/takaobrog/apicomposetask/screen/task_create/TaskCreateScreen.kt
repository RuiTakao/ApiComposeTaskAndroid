package com.takaobrog.apicomposetask.screen.task_create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takaobrog.apicomposetask.R
import com.takaobrog.apicomposetask.screen.task_create.model.TaskCreateEvent
import com.takaobrog.apicomposetask.screen.task_create.model.TaskCreateFormState
import com.takaobrog.component.component.app_bar.DefaultTopAppBarBack
import com.takaobrog.component.component.button.DefaultButton
import com.takaobrog.component.component.input_field.DateInputField
import com.takaobrog.component.component.input_field.InputTextAreaField
import com.takaobrog.component.component.input_field.InputTextField

@Composable
fun TaskCreateScreen(
    formState: TaskCreateFormState,
    onEvent: (TaskCreateEvent) -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            DefaultTopAppBarBack(
                onClick = { onEvent(TaskCreateEvent.OnBackEvent) },
                title = stringResource(id = R.string.task_create_title)
            )
        },
    ) { paddingValues ->
        Form(
            formState = formState,
            onEvent = onEvent,
            modifier = Modifier.padding(paddingValues = paddingValues)
        )
    }
}

@Composable
private fun Form(
    formState: TaskCreateFormState,
    onEvent: (TaskCreateEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        InputTextField(
            label = stringResource(id = R.string.task_create_form_title),
            value = formState.title,
            onValueChange = { onEvent(TaskCreateEvent.OnValueChangeTitle(title = it)) },
        )

        DateInputField(
            label = stringResource(id = R.string.task_create_form_target_date),
            value = formState.formatTargetDate,
            onValueChange = { onEvent(TaskCreateEvent.OnValueChangeTargetDate(targetDate = it)) },
        )

        InputTextAreaField(
            label = stringResource(id = R.string.task_create_form_comment),
            value = formState.comment,
            onValueChange = { onEvent(TaskCreateEvent.OnValueChangeComment(comment = it)) },
        )

        DefaultButton(
            text = stringResource(id = R.string.task_create_form_submit),
            onClick = { onEvent(TaskCreateEvent.OnSubmit) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskCreateScreen_Preview() {
    TaskCreateScreen(formState = TaskCreateFormState(), onEvent = {})
}

@Preview(showBackground = true)
@Composable
fun TaskCreateScreen_Preview_Value() {
    TaskCreateScreen(
        formState = TaskCreateFormState(
            title = "API学習",
            comment = "API学習中\nlesson2",
            formatTargetDate = "2026/5/10"
        ),
        onEvent = {},
    )
}