package com.am.dzenlyst.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroScreen

@Composable
fun Navigation(navController: NavHostController = rememberNavController()){
    NavHost(navController = navController, startDestination = Screen.Pomodoro.route) {
        composable(Screen.Pomodoro.route) {
            PomodoroScreen()
        }
    }
}