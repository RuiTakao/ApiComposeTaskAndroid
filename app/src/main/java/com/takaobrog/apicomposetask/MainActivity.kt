package com.takaobrog.apicomposetask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.takaobrog.apicomposetask.route.ScreenRoute
import com.takaobrog.apicomposetask.route.taskListRoute
import com.takaobrog.apicomposetask.ui.theme.ApiComposeTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApiComposeTaskTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenRoute.TaskList.route,
                ) {
                    taskListRoute(navController = navController)
                }
            }
        }
    }
}