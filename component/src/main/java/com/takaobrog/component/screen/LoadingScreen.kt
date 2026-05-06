package com.takaobrog.component.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.component.R

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    color: Color = ProgressIndicatorDefaults.circularColor,
    alpha: Float = 0f,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(enabled = false) {}
            .background(
                color = colorResource(id = R.color.reloading_background_color)
                    .copy(alpha = alpha)
            ),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(color = color)
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreen_Preview() {
    LoadingScreen()
}