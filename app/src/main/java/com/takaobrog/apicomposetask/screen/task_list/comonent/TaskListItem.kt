package com.takaobrog.apicomposetask.screen.task_list.comonent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takaobrog.apicomposetask.R
import com.takaobrog.component.component.DefaultText
import com.takaobrog.component.component.ProgressPercentItem
import com.takaobrog.component.component.TargetDateText

@Composable
fun TaskListItem(
    title: String,
    progressPercent: Float,
    targetDate: String?,
    isTargetDateOver: Boolean,
    onItemClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() },
        shape = RoundedCornerShape(size = 16.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.task_list_item_base_color)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        TitleView(title = title, isTargetDateOver = isTargetDateOver)
        DetailView(progressPercent = progressPercent, targetDate = targetDate)
    }
}

@Composable
private fun TitleView(title: String, isTargetDateOver: Boolean) {
    DefaultText(
        text = title,
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
        color = if (!isTargetDateOver) Color.Black else Color.Red,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun DetailView(progressPercent: Float, targetDate: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.base_background_color))
            .padding(start = 16.dp, top = 10.dp, bottom = 16.dp)
    ) {
        ProgressPercentItem(
            label = stringResource(id = R.string.task_list_item_progress_percent_label),
            progressPercent = progressPercent,
        )
        TargetDateText(targetDate = targetDate, modifier = Modifier.padding(top = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListItem_Preview() {
    TaskListItem(
        title = "Room学習",
        progressPercent = .3f,
        targetDate = "4/5 12:00",
        isTargetDateOver = false,
        onItemClick = {},
    )
}

@Preview(showBackground = true)
@Composable
fun TaskListItem_Preview_IsTargetDateOver() {
    TaskListItem(
        title = "Firebase学習",
        progressPercent = .7f,
        targetDate = "4/1 9:00",
        isTargetDateOver = true,
        onItemClick = {},
    )
}

@Preview(showBackground = true)
@Composable
fun TaskListItem_Preview_Max() {
    TaskListItem(
        title = "Firebase学習",
        progressPercent = 1f,
        targetDate = "4/1 9:00",
        isTargetDateOver = true,
        onItemClick = {},
    )
}

@Preview(showBackground = true)
@Composable
fun TaskListItem_Preview_Min() {
    TaskListItem(
        title = "Api学習",
        progressPercent = .0f,
        targetDate = null,
        isTargetDateOver = false,
        onItemClick = {},
    )
}