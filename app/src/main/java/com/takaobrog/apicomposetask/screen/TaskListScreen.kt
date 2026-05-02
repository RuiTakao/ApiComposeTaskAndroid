package com.takaobrog.apicomposetask.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TaskListScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
    ) { paddingValues ->
        Column(modifier.padding(paddingValues = paddingValues)) {
            Text(text = "TaskListScreen")
            Button(onClick = onClick) {
                Text(text = "test")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListScreen_Preview() {
    TaskListScreen(onClick = {})
}