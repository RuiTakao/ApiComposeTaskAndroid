package com.takaobrog.apicomposetask.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.apicomposetask.R

@Composable
fun TargetDateText(targetDate: String?, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        DefaultText(
            text = "${stringResource(id = R.string.target_date_text_label)}: ",
            fontWeight = FontWeight.Bold,
        )
        DefaultText(text = targetDate ?: stringResource(id = R.string.target_date_text_empty))
    }
}

@Preview(showBackground = true)
@Composable
fun TargetDateText_Preview() {
    TargetDateText(targetDate = "2026/4/30")
}

@Preview(showBackground = true)
@Composable
fun TargetDateText_Preview_null() {
    TargetDateText(targetDate = null)
}