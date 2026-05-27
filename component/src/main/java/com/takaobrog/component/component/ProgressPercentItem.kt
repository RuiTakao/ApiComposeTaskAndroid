package com.takaobrog.component.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takaobrog.component.R

@Composable
fun ProgressPercentItem(
    label: String,
    progressPercent: Float,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        DefaultText(text = "$label: ", fontWeight = FontWeight.Bold)
        LinearProgressIndicator(
            progress = { progressPercent },
            modifier = Modifier
                .height(height = 16.dp)
                .padding(start = 4.dp),
            color = colorResource(id = R.color.percent_progress_color),
            trackColor = colorResource(id = R.color.percent_progress_track_color),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressPercentView_Preview() {
    ProgressPercentItem(label = "進捗", progressPercent = 0.3f)
}

@Preview(showBackground = true)
@Composable
fun ProgressPercentView_Preview_ZERO() {
    ProgressPercentItem(label = "進捗", progressPercent = 0f)
}

@Preview(showBackground = true)
@Composable
fun ProgressPercentView_Preview_Max() {
    ProgressPercentItem(label = "進捗", progressPercent = 1f)
}