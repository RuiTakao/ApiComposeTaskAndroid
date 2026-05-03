package com.takaobrog.apicomposetask.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.takaobrog.apicomposetask.R

@Composable
fun DefaultText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = colorResource(R.color.base_color),
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultText_Preview() {
    DefaultText(text = "Retrofit学習")
}