package com.takaobrog.apicomposetask.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.takaobrog.apicomposetask.R
import com.takaobrog.component.component.DefaultText
import com.takaobrog.component.component.button.DoubleButton
import com.takaobrog.component.component.scrollable.ScrollableColumn

@Composable
fun TaskDetailItem(
    title: String,
    comment: String,
    progressPercent: Float,
    targetDate: String,
    onClickEditButton: () -> Unit,
    onClickDeleteButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ScrollableColumn(modifier = modifier.padding(all = 16.dp)) {
        DefaultText(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        ProgressPercentItem(
            label = stringResource(id = R.string.task_detail_item_progress_percent_label),
            progressPercent = progressPercent,
            modifier = Modifier.padding(top = 16.dp),
        )
        TargetDateText(targetDate, modifier = Modifier.padding(top = 8.dp))
        DefaultText(
            text = comment,
            modifier = Modifier.padding(top = 8.dp)
        )
        DoubleButton(
            leftButtonText = stringResource(id = R.string.task_detail_item_edit_button),
            rightButtonText = stringResource(id = R.string.task_detail_item_delete_button),
            onClickLeftButton = onClickEditButton,
            onClickRightButton = onClickDeleteButton,
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailItem_Preview() {
    TaskDetailItem(
        title = "Api学習",
        comment = "API　サーバ作成",
        progressPercent = .3f,
        targetDate = "2026/5/14",
        onClickEditButton = {},
        onClickDeleteButton = {}
    )
}