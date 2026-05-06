package com.takaobrog.component.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.takaobrog.component.R

@Composable
fun FAButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.fab_icon_description),
                tint = colorResource(id = R.color.fab_icon_color),
                modifier = Modifier.size(size = 32.dp)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun FAButtonPreview() {
    FAButton(onClick = {})
}