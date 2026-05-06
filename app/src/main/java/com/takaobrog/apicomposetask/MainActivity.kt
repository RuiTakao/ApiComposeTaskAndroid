package com.takaobrog.apicomposetask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                    composable(route = ScreenRoute.TaskCreate.route) {
                        Button(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.padding(all = 100.dp),
                        ) {
                            Text(text = "TaskCreate")
                        }
                    }
                    composable(
                        route = "${ScreenRoute.TaskDetail.route}/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) {
                        Button(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.padding(all = 100.dp),
                        ) {
                            Text(text = "TaskCreate ${it.arguments?.getInt("id")}")
                        }
                    }
                }
            }
        }
    }
}