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
import com.takaobrog.component.component.app_bar.DefaultTopAppBarBack
import com.takaobrog.component.component.button.DefaultButton
import com.takaobrog.component.component.input_field.DateInputField
import com.takaobrog.component.component.input_field.InputTextAreaField
import com.takaobrog.component.component.input_field.InputTextField

@Composable
fun TaskCreateScreen() {
    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            DefaultTopAppBarBack(
                onClick = { },
                title = stringResource(id = R.string.task_create_title)
            )
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues = paddingValues)) {
            InputTextField(
                label = stringResource(id = R.string.task_create_form_title),
                value = "",
                onValueChange = { },
            )

            DateInputField(
                label = stringResource(id = R.string.task_create_form_target_date),
                value = null,
                onValueChange = { },
            )

            InputTextAreaField(
                label = stringResource(id = R.string.task_create_form_comment),
                value = "",
                onValueChange = { },
            )

            DefaultButton(
                text = stringResource(id = R.string.task_create_form_submit),
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskCreateScreen_Preview() {
    TaskCreateScreen()
}