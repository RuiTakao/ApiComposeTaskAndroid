package com.takaobrog.component.component.app_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.takaobrog.component.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBarBack(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = "",
) {
    TopAppBar(
        title = { Text(text = title, fontWeight = FontWeight.Bold) },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_button_description),
                    tint = Color(0xFF854A2A)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultTopAppBarBack_Preview() {
    DefaultTopAppBarBack(onClick = {}, title = "タスク作成")
}