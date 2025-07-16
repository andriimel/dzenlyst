package com.am.dzenlyst.ui.navigation

import StatsScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.am.dzenlyst.ui.screens.Coach.CoachScreen
import com.am.dzenlyst.ui.screens.PomodoScreen.BottomNavBar
import com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroScreen
import com.am.dzenlyst.ui.screens.PomodoScreen.PomodoroViewModel
import com.am.dzenlyst.ui.screens.Tasks.TasksScreen

@Composable
fun Navigation(viewModel: PomodoroViewModel= hiltViewModel()){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            BottomNavBar(
                selected = currentRoute ?: "focus",
                onItemSelected = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Focus.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screens.Focus.route) { PomodoroScreen(navController) }
            composable(Screens.Tasks.route) { TasksScreen() }
            composable(Screens.Stats.route) { StatsScreen() }
            composable(Screens.Coach.route) { CoachScreen() }
        }
    }
}