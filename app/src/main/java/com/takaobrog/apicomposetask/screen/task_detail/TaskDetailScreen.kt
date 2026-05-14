package com.takaobrog.apicomposetask.screen.task_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.takaobrog.apicomposetask.R
import com.takaobrog.apicomposetask.component.ProgressPercentItem
import com.takaobrog.apicomposetask.component.TargetDateText
import com.takaobrog.component.component.DefaultText
import com.takaobrog.component.component.app_bar.DefaultTopAppBarBack
import com.takaobrog.component.component.button.DoubleButton

@Composable
fun TaskDetailScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            DefaultTopAppBarBack(onClick = {})
        },
        contentWindowInsets = WindowInsets.systemBars,
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues = paddingValues)
                .padding(all = 16.dp)
        ) {
            DefaultText(
                text = "Api学習",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            ProgressPercentItem(
                label = stringResource(id = R.string.task_detail_item_progress_percent_label),
                progressPercent = .3f,
                modifier = Modifier.padding(top = 16.dp),
            )
            TargetDateText("2026/5/14", modifier = Modifier.padding(top = 8.dp))
            DefaultText(text = "API　サーバ作成", modifier = Modifier.padding(top = 8.dp))
            DoubleButton(
                leftButtonText = stringResource(id = R.string.task_detail_item_edit_button),
                rightButtonText = stringResource(id = R.string.task_detail_item_delete_button),
                onClickLeftButton = { },
                onClickRightButton = { },
                modifier = Modifier.padding(top = 16.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailScreen_Preview() {
    TaskDetailScreen()
}
