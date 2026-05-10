package com.takaobrog.component.component.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Bold,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(size = 8.dp),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(4.dp),
            fontSize = 16.sp,
            fontWeight = fontWeight,
        )
    }
}

@Preview
@Composable
fun DefaultButton_Preview() {
    DefaultButton(
        text = "作成",
        onClick = {},
    )
}

@Preview
@Composable
fun DefaultButton_Preview_normal() {
    DefaultButton(
        text = "作成",
        onClick = {},
        fontWeight = FontWeight.Normal
    )
}