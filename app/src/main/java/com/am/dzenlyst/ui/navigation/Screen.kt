package com.am.dzenlyst.ui.navigation

sealed  class Screen(val route: String) {

    object Pomodoro : Screen("pomodoro")
    object Tasks : Screen("tasks")
    object Stats : Screen("stats")
    object Coach: Screen("coach")
}